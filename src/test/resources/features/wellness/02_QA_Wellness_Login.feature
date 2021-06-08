@Regression @function=02_QA_Wellness
Feature: Verify if the Master Avatar can see the correct UI

  @02_QA_Welness_04
  Scenario: Verify if the Master Avatar can see the correct UI
    Given User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
    Then A "Master Avatar" validates tabs link in Wellness page

  @02_QA_Welness_05
  Scenario: Verify if the Yogi can see the correct UI
    Given User access the Wellness Home page
    When A wellness "Yogi" user logs in
    Then A "Yogi" validates tabs link in Wellness page

  @02_QA_Welness_06
  Scenario: Verify if the Test Clinician can see the correct UI
    Given User access the Wellness Home page
    When A wellness "Test clinician" user logs in
    Then A "Test clinician" validates tabs link in Wellness page