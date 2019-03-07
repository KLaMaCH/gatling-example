package application.jira.common

import application.jira.JIRA
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

import scala.concurrent.duration._

class SearchWithJQLRest extends JIRA {

  val searchWithJQL: ScenarioBuilder = scenario("Search Issues with JQL priority = High")
    .group("Jira - Search With JQL") {
      exec(
        JIRA.JQLSearchRest
      )
    }

  var testName = "Search Issues with JQL"

  // users injection and assertion setup here
  setUp(
    searchWithJQL.inject(
      nothingFor(5 seconds),
      heavisideUsers(numberOfUsers) over durationInSeconds
    )
  ).protocols(httpProtocol)
    .assertions(
      global.responseTime.max.lt(1000)
    )
}
