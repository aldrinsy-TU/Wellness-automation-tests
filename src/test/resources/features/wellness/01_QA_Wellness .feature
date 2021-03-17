@Regression @function=01_QA_Wellness
Feature: Verify if user is able to submit a session Request

  @01_QA_Welness_01
  Scenario: To Verify if user is able to submit a session Request
    Given User access the Boost Home page
    When A wellness employee user logs in
    And User create Session Request

  @01_QA_Welness_02
  Scenario: Verify if user is able to check if there's a Survey form when session is created
    Given User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
      Then User clicks on Requests link
    And User select "New" status for request filter
      Then Validate newly added record
    And Assign "Master Avatar" coach to a request
    And Validate that status is changed to "Assigned"
    And Update request status to "Ongoing"
    And Create session log on request
    And User insert sample data to Individual Session form
      Then User refresh the page
      Then User clicks on Requests link
      Then User refresh the page
    And Validate that status is changed to "Ongoing"
      Then User clicks on Past Forms Link
    And Select date in Past Form using recent request date
    And Validate past created session
      Given User access the Boost Home page
      When A wellness employee user logs in
      Then User clicks on Wellness
      And Select date in wellness page
      And User take the survey at wellness
    And Validate survey button is removed

  @01_QA_Welness_03
  Scenario: Verify if user is able to create a session log per month
    Given User access the Boost Home page
    When A wellness employee user logs in
    Then User clicks on Wellness
    Then User clicks checkin
    And User clicks on check-in and insert sample data on check-in Form

# Dev notes
#    Given Test CSVWriter
#    Given test update CSV "Assigned" role "Master Avatar"
#    mvn clean verify -Denvironment=default -Dcucumber.options="--tags @function=01_QA_Wellness"