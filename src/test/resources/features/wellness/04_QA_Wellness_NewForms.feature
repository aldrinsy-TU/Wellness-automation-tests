@Regression @function=04_QA_Wellness
Feature: New/Past Forms

  @04_QA_Welness_19
  Scenario: Verify if Yogi user is able to create a Group Form
    Given User access the Wellness Home page
    When A wellness "Yogi" user logs in
    Then User clicks on Group Form link
    And Input data on Group Form
    Then User clicks on Past Forms Link
    And Select date in Past Form using session request date
    And Validate Group Form Session

  @04_QA_Welness_20
  Scenario: Verify if Yogi user is able to create a Team meeting with TL Form
    Given User access the Wellness Home page
    When A wellness "Yogi" user logs in
    Then User clicks on Team Meeting with TL Form
    And Input data on Team Meeting with TL Form
    Then User clicks on Past Forms Link
    And Select date in Past Form using session request date
    And Validate Team Meeting with TL Form Session

  @04_QA_Welness_21
  Scenario: Verify if Yogi user is able to create a Individual Session Form
    Given User access the Wellness Home page
    When A wellness "Yogi" user logs in
    Then User clicks on Individual Session Form
    And Input data on Individual Session Form
    Then User clicks on Past Forms Link
    And Select date in Past Form using session request date
    And Validate Individual Session Form Session

  @04_QA_Welness_22
  Scenario: Verify if Test Clinician user is able to create a Group Form
    Given User access the Wellness Home page
    When A wellness "Test clinician" user logs in
    Then User clicks on Group Form link
    And Input data on Group Form
    Then User clicks on Past Forms Link
    And Select date in Past Form using session request date
    And Validate Group Form Session

  @04_QA_Welness_23
  Scenario: Verify if Test Clinician user is able to create a Team meeting with TL Form
    Given User access the Wellness Home page
    When A wellness "Test clinician" user logs in
    Then User clicks on Team Meeting with TL Form
    And Input data on Team Meeting with TL Form
    Then User clicks on Past Forms Link
    And Select date in Past Form using session request date
    And Validate Team Meeting with TL Form Session

  @04_QA_Welness_24
  Scenario: Verify if Test Clinician user is able to create a Individual Session Form
    Given User access the Wellness Home page
    When A wellness "Test clinician" user logs in
    Then User clicks on Individual Session Form
    And Input data on Individual Session Form
    Then User clicks on Past Forms Link
    And Select date in Past Form using session request date
    And Validate Individual Session Form Session

  @04_QA_Welness_25
  Scenario: Verify if Master Avatar user is able to create a Group Form
    Given User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
    Then User clicks on Group Form link
    And Input data on Group Form
    Then User clicks on Past Forms Link
    And Select date in Past Form using session request date
    And Validate Group Form Session

  @04_QA_Welness_26
  Scenario: Verify if Master Avatar user is able to create a Team meeting with TL Form
    Given User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
    Then User clicks on Team Meeting with TL Form
    And Input data on Team Meeting with TL Form
    Then User clicks on Past Forms Link
    And Select date in Past Form using session request date
    And Validate Team Meeting with TL Form Session

  @04_QA_Welness_27
  Scenario: Verify if Master Avatar user is able to create a Individual Session Form
    Given User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
    Then User clicks on Individual Session Form
    And Input data on Individual Session Form
    Then User clicks on Past Forms Link
    And Select date in Past Form using session request date
    And Validate Individual Session Form Session

  @04_QA_Welness_28
  Scenario: Verify the content of the Leadership Departmental Training Log Form
    Given User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
    Then User clicks on Go to Leadership and Departmental Training Log
    And User insert sample data to Leadership and Departmental Training Log
    Then User clicks on Past Forms Link
    And Select date in Past Form using session request date
    And Validate Leadership and Departmental Training Log Form Session

  @04_QA_Welness_29
  Scenario: Verify the content of the Debrief/Stand-Up Skill Form
    Given User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
    Then User clicks on  Go to Debrief and Stand-Up Skill
    And User insert sample data to Debrief and Stand-Up Skill
    Then User clicks on Past Forms Link
    And Select date in Past Form using session request date
    And Validate Debrief and Stand-Up Skill Log Form Session
