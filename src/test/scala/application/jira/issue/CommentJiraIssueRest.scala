package application.jira.issue

import application.jira.JIRA
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

import scala.concurrent.duration._

class CommentJiraIssueRest extends JIRA {
  // You need to feed scenario by issueKeys somehow e.g. from csv
  val csvFeeder = csv("Jira_Issue_Ids_Keys.csv").random

  val commentIssue: ScenarioBuilder = scenario("Commenting a Jira Issue using REST")
    .feed(csvFeeder)
    .group("Jira - Comment Issue") {
      feed(csvFeeder)
        .exec(
          JIRA.commentIssueRest
        )
    }

  var testName = "Comment Jira Issue"

  // users injection and assertion setup here
  setUp(
    commentIssue.inject(
      nothingFor(5 seconds),
      heavisideUsers(numberOfUsers) over durationInSeconds
    )
  ).protocols(httpProtocol)
    .assertions(
      global.responseTime.max.lt(1000)
    )
}
