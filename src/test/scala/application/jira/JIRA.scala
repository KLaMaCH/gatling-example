package application.jira

import helper.HelperClass
import io.gatling.core.Predef._
import io.gatling.http.Predef.{http, jsonPath}
import io.gatling.http.request.builder.HttpRequestBuilder


trait JIRA extends HelperClass {

  object JIRA {

    val createIssueRest: HttpRequestBuilder = http("Creating issue using REST")
      .post("/rest/api/2/issue")
      .headers(headers_content)
      .basicAuth(userName, password)
      // all JSON bodies can be customize by using variables
      .body(ElFileBody("IssueCreateBody.json")).asJSON

    val editIssueRest: HttpRequestBuilder = http("Editing issue using REST")
      .put("/rest/api/2/issue/${issuekey}")
      .headers(headers_content)
      .basicAuth(userName, password)
      .body(ElFileBody("IssueEditBody.json")).asJSON

    val commentIssueRest: HttpRequestBuilder = http("Commenting issue using REST")
      .post("/rest/api/2/issue/${issuekey}/comment")
      .headers(headers_content)
      .basicAuth(userName, password)
      .body(ElFileBody("IssueCommentBody.json")).asJSON

    val JQLSearchRest: HttpRequestBuilder = http("JQL Search using REST")
      .post("/rest/api/2/search")
      .headers(headers_content)
      .basicAuth(userName, password)
      .body(ElFileBody("JQLSearch.json")).asJSON

    val tokenSavingExample: HttpRequestBuilder = http("Saving token from response to session")
      .post("/secure/QuickEditIssue!default.jspa?issueId=${issueId}&decorator=none")
      .basicAuth(userName, password)
      .check(
        jsonPath("$.atl_token").saveAs("atl_token"),
        jsonPath("$.formToken").saveAs("formToken")
      )

  }
}
