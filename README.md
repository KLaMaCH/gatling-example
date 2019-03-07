# Gatling Tests for Jira example
This is a project with few performance tests for Atlassian Jira using Gatling.
There is only REST API tests without additional endpoinds and resourses such:
* Create Jira Issue
* Edit Jira Issue
* Comment Jira Issue
* JQL Search

I have used Maven plugin for starting of simulation, configuration examples you can find here: https://gatling.io/docs/current/extensions/maven_plugin/

For starting all simulation classes use command
mvn gatling:test

Test reports can be found in target/gatling dir

Full Gatling Documentation can be found here: https://gatling.io/docs/current/