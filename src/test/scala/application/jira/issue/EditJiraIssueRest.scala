package application.jira.issue

import application.jira.JIRA
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

import scala.concurrent.duration._

class EditJiraIssueRest extends JIRA {
  // You need to feed scenario by issueKeys somehow e.g. from csv
  val csvFeeder = csv("Jira_Issue_Ids_Keys.csv").random

  val editIssue: ScenarioBuilder = scenario("Editing the Summary, Description and priority existing Issue.")
    .feed(csvFeeder)
    .group("Jira - Edit Issue") {
      exec(
        JIRA.editIssueRest
      )
    }


  var testName = "Edit Jira Issue"

  // users injection and assertion setup here
  setUp(
    editIssue.inject(
      nothingFor(5 seconds),
      heavisideUsers(numberOfUsers) over durationInSeconds
    )
  ).protocols(httpProtocol)
    .assertions(
      global.responseTime.max.lt(1000)
    )
}
