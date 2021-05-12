@Regression @function=05_QA_Wellness
Feature: Wellness monthly and weekly Reporting

  @05_QA_Welness_28-29
  Scenario: Verify if Master Avatar is able to Filter Coach Monthly Report
    Given User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
    Then User clicks on Reporting Link
    Then User clicks "Coach" Report Link
    Then Validate Coach report for "Pamatmat, Monica Vida" is valid

  @05_QA_Welness_30
  Scenario: Verify if Master Avatar is able to Filter Status Monthly Report
    Given User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
    Then User clicks on Reporting Link
    Then User clicks "Status" Report Link
    And Validate status report
# create bagong validation

  @05_QA_Welness_31
  Scenario: Verify if Master Avatar is able to Filter Site Monthly/Weekly Report
    Given User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
    Then User clicks on Reporting Link
    Then User clicks "Site" Report Link
    Then Select Monthly Site Report Date and Site
    Then Select Weekly Site Report Date and Site
    Then Validate Site report for "Chateau Ridiculous" is valid

  @05_QA_Welness_32
  Scenario: Verify if Master Avatar is able to Filter data on the Global Report (Check-in,'Coaches and Wellness and Resiliency Manager Session','Group Form Sessions',and 'Mental Health Professional')
    Given User access the Wellness Home page
    When A wellness "Master Avatar" user logs in
    Then User clicks on Reporting Link
    Then User clicks "Global Reports" Report Link
    Then Select Global Site Report Date From and To
    #lagay ng validation
    Then Select "Check in" Report From Global Reports
    Then Select "Coaches and Wellness and Resiliency Manager Sessions" Report From Global Reports
    Then Select "Group Form Sessions" Report From Global Reports
    Then Select "Mental Health Professional Sessions" Report From Global Reports