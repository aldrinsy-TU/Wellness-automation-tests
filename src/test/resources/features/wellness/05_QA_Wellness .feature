@Regression @function=05_QA_Wellness
Feature: Report

  @05_QA_Welness_28-29
  Scenario: Verify if Master Avatar is able to Filter Coach Monthly Report
    Given User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
    Then User clicks on Reporting Link
    Then User clicks "Coach" Report Link
    Then Validate Coach report for "Dinglasan, Donnalie" is valid

  @05_QA_Welness_30
  Scenario: Verify if Master Avatar is able to Filter Status Monthly Report
    Given User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
    Then User clicks on Reporting Link
    Then User clicks "Status" Report Link
    And Validate status report
