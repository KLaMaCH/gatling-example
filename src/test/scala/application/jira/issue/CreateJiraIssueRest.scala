package application.jira.issue

import application.jira.JIRA
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

import scala.concurrent.duration._

class CreateJiraIssueRest extends JIRA {
  val issueType: Int = System.getProperty("jiraIssueType","10002").toInt

  val createIssue: ScenarioBuilder = scenario("Create a Jira Issue")
    // there is no need to set issueType because of it already declared in JSON body
    // just an example how to work with session
    .exec(
      session => {
        session
          .set("issueType",issueType)
      }
    )
    .group("Jira - Create Issue") {
        exec(
          JIRA.createIssueRest
        )
    }

  var testName = "Create Jira Issue"

  // users injection and assertion setup here
  setUp(
    createIssue.inject(
      nothingFor(5 seconds),
      heavisideUsers(numberOfUsers) over durationInSeconds
    )
  ).protocols(httpProtocol)
    .assertions(
      global.responseTime.max.lt(1000)
    )

}
