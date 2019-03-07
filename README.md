# Gatling Tests for Jira example
This is a project with few performance tests for Atlassian Jira using Gatling.
There is only REST API tests without additional endpoinds and resourses e.g.:
* Create Jira Issue
* Edit Jira Issue
* Comment Jira Issue
* JQL Search

I have used Maven plugin for starting of simulation, configuration examples you can find here: https://gatling.io/docs/current/extensions/maven_plugin/

All test classes placed in src/test/scala/application/jira/ dir
Main configuration class placed here All test classes placed in src/test/scala/helper dir
You should check it first for defining your Jira instance base url etc.

For starting all simulation classes use command
mvn gatling:test

Test reports can be found in target/gatling dir

Full Gatling Documentation can be found here: https://gatling.io/docs/current/
