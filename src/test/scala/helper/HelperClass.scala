package helper

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration._


trait HelperClass extends Simulation {
  // url is hardcoded, but it can be replaced by system property
  val base_url: String = "http://yourjirainstance.com"
  // user is hardcoded, but you can use feeder in scenarios itself
  val userName = "admin"
  val password = "admin"
  // number of users and duration getting from properties
  val numberOfUsers: Int = System.getProperty("numberOfUsers","100").toInt
  val durationInSeconds: FiniteDuration = System.getProperty("durationInSeconds","100").toInt.seconds

  val httpProtocol: HttpProtocolBuilder = http
    .baseURL(base_url)
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .check(status.is(200))

  val headers_content = Map(
    "Content-Type" -> "application/json")

  var testName: String // abstract

  /* Place for arbitrary Scala code that is to be executed before the simulation begins. */
  before {
    println("***** " + testName + " simulation is about to begin! *****")
  }

  /* Place for arbitrary Scala code that is to be executed after the simulation has ended. */
  after {
    println("***** " + testName + " simulation has ended! ******")
  }
}
