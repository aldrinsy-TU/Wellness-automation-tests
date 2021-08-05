@Regression @function=03_QA_Wellness
Feature: 03_QA_Wellness_RequestModule

  @03_QA_Welness_07
  Scenario: Verify if Master Avatar can assign to himself
    Given User access the Boost Home page
    When A wellness employee user logs in
    And User create Session Request
    Then User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
    Then User clicks on Requests link
    And User select "New" status for request filter
    Then Validate newly added record
    And Assign "Master Avatar" coach to a request
    And Validate that status is changed to "Assigned"

  @03_QA_Welness_08
  Scenario: Verify if Master Avatar can view Registration
    Given User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
    Then User clicks on Requests link
    And Validate that status is changed to "Assigned"
    And Validate request Registration

   @03_QA_welness_09
   Scenario: Verify if the Master avatar can create a session log
    Given User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
    Then User clicks on Requests link
    And Validate that status is changed to "Assigned"
    And Update request status to "Ongoing"
    And Create session log on request
    And User insert sample data to Individual Session form
    Then User refresh the page
    Then User clicks on Requests link
    Then User refresh the page
    And Validate that status is changed to "Ongoing"

  @03_QA_welness_10
  Scenario: Verify created Session of the Master Avatar is on the Past Forms tab
    Given User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
    Then User clicks on Past Forms Link
    And Select date in Past Form using recent request date
    And Validate past created session

   @03_QA_welness_11
   Scenario: Verify if Master Avatar is able to complete a Session
    Given User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
    Then User clicks on Requests link
    And Validate that status is changed to "Ongoing"
    And Update request status to "Completed"
    Then User clicks on Requests link
    Then User refresh the page
    And Validate that status is changed to "Completed"

  @03_QA_welness_12
  Scenario: Verify if Master Avatar can re-assign Yogi
    Given User access the Boost Home page
    When A wellness employee user logs in
    And User create Session Request
    Then User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
    Then User clicks on Requests link
    And User select "New" status for request filter
    Then Validate newly added record
    And Assign "Master Avatar" coach to a request
    And Validate that status is changed to "Assigned"
    And Reassign "Yogi" coach to a request
    And Validate that status is changed to "Reassigned"
    And Reassign "Yogi2" coach to a request
    And Validate that status is changed to "Reassigned"

  @03_QA_welness_13
  Scenario: Verify if Yogi is not able to see other session that's not assigned to him/her
    Given User access the Wellness Home page
    When A wellness "Yogi" user logs in
    Then User clicks on Requests link
    And User select "Assigned" status for request filter
    Then Validate that there's no assigned session

  @03_QA_welness_14
  Scenario: Verify if the Yogi can only see the assigned Request by the Master Avatar
    Given User access the Wellness Home page
    When A wellness "Yogi2" user logs in
    Then User clicks on Requests link
    And Validate that status is changed to "Reassigned"

  @03_QA_welness_15
   Scenario: Verify if the Yogi can create a session log
    Given User access the Wellness Home page
    Given that fetch session assigned for "Yogi2"
    When A wellness "Yogi2" user logs in
    Then User clicks on Requests link
    And Validate that status is changed to "Reassigned"
    And Update request status to "Ongoing"
    And Create session log on request
    And User insert sample data to Individual Session form
    Then User refresh the page
    Then User clicks on Requests link
    Then User refresh the page
    And Validate that status is changed to "Ongoing"

  @03_QA_welness_16
  Scenario: Verify created Session of the Yogi on the Past Forms tab
    Given User access the Wellness Home page
    When A wellness "Yogi2" user logs in
    Then User clicks on Past Forms Link
    And Select date in Past Form using recent request date
    And Validate past created session

  @03_QA_welness_17
  Scenario: Verify if Yogi is able to complete a Session
    Given User access the Wellness Home page
    Given that fetch session assigned for "Yogi2"
    When A wellness "Yogi2" user logs in
    Then User clicks on Requests link
    And Validate that status is changed to "Ongoing"
    And Update request status to "Completed"
    And Validate that status is changed to "Completed"

  @03_QA_welness_18
  Scenario: Verify if Test clinician will not receive a session
    Given User access the Wellness Home page
    When A wellness "Test clinician" user logs in
    Then User clicks on Requests link
    And User select "Assigned" status for request filter
    Then Validate that there's no assigned session

  @03_QA_welness_19
  Scenario: Verify if the Director or Sr Director Account is able re-assign a session
    Given User access the Boost Home page
    When A wellness employee user logs in
    And User create Session Request
    Given User access the Wellness Home page
    Then A wellness "Master Avatar" user logs in
    Then User clicks on Requests link
    And User select "New" status for request filter
    Then Validate newly added record
    And Assign "Master Avatar" coach to a request
    And Logout account in Wellness
    Then User access the Wellness Home page
    Then A wellness "Director" user logs in
    Then User clicks on Requests link
    And User select "Assigned" status for request filter
    And Validate that status is changed to "Assigned"
    And Reassign "Yogi" coach to a request
    And Validate that status is changed to "Reassigned"

  @03_QA_welness_20
  Scenario: Verify if there's already a Coachee EID Column and Sort Functionality on the Requests Grid
    Given User access the Wellness Home page
    Then A wellness "Master Avatar" user logs in
    Then User clicks on Requests link
    Then Validate that coachee EID column is now available
    Then Click coachee EID column header
    Then Click status column header
    Then Validate first row is changed