@Regression @function=01_QA_Wellness
Feature: 01_QA_Wellness_Boost

  @01_QA_Welness_01
  Scenario: Verify if the lists of Sessions will reflect correctly on the grid view and Registration Form fields should be correct
    Given Clear wellness database
#    Given User access the Boost Home page
#    Then User refresh the page
#    When A wellness employee user logs in
#    And User create Session Request
#    Then User access the Wellness Home page
#    When A wellness "Master Avatar" user logs in
#    Then User clicks on Requests link
#    And User select "New" status for request filter
#    Then Validate newly added record
#    And Assign "Master Avatar" coach to a request
#    And Validate that status is changed to "Assigned"
#    And Update request status to "Ongoing"
#    And Create 6 individual session log
#    Then User access the Boost Home page
#    When A wellness employee user logs in
#    Then User refresh the page
#    Then User clicks on Wellness
#    And User clicks on check-in and insert sample data on check-in Form
#    And Validate check-in button is disabled
#
#  @01_QA_Welness_02
#  Scenario: To Verify if user is able to submit a session Request
#    Given Clear wellness database
#    Given User access the Boost Home page
#    When A wellness employee user logs in
#    And User create Session Request
#    Then User access the Wellness Home page
#    When A wellness "Master Avatar" user logs in
#    Then User clicks on Requests link
#    And User select "New" status for request filter
#    Then Validate newly added record
#
#  @01_QA_Welness_03
#  Scenario: Verify if user is able to check if there's a Survey form when session is created
#    Given User access the Wellness Home page
#    When A wellness "Master Avatar" user logs in
#      Then User clicks on Requests link
#    And User select "New" status for request filter
#      Then Validate newly added record
#    And Assign "Master Avatar" coach to a request
#    And Validate that status is changed to "Assigned"
#    And Update request status to "Ongoing"
#    And User select "Ongoing" status for request filter
#    And Create session log on request
#    And User insert sample data to Individual Session form
#      Then User refresh the page
#      Then User clicks on Requests link
#      Then User refresh the page
#    And Validate that status is changed to "Ongoing"
#      Then User clicks on Past Forms Link
#    And Select date in Past Form using recent request date
#    And Validate past created session
#      Given User access the Boost Home page
#      When A wellness employee user logs in
#      Then User clicks on Wellness
#      And Select date in wellness page
#      And User take the survey at wellness
#    And Validate survey button is removed
#
#  @01_QA_Welness_04
#  Scenario: Verify if user is able to create a session log per month
#    Given User access the Boost Home page
#    When A wellness employee user logs in
#    Then User clicks on Wellness
#    And User clicks on check-in and insert sample data on check-in Form
#    And Validate check-in button is disabled