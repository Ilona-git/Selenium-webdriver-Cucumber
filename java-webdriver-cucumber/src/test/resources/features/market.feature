@market
Feature: Marketing app test suite

      #tag for first scenario
  @market1
  Scenario: Market basic test
    Given I go to "quote" page
    When I verify email field behavior
    And print page details in console
    And I go back and forward, then refresh the page
    And I fill out required fields
    And And I fill out optional fields
    And I submit the form
    Then I wait for 4 sec
    And I change resolution to "phone" and then to "desktop"

    Then I verify that submitted fields saved correctly


  @market2
  Scenario: Market optional fields
    Given I go to "quote" page
    When I fill out required fields

    And I submit the form
    Then I verify required fields
    Then I verify optional fields

  @market3
  Scenario: Printing logs
    Given I go to "yahoo" page
    And I print logs to the console

  @market4
 Scenario: Multi-select
    Given I go to "quote" page
    And I fill multu-select

  @market5_alert
  Scenario: Quote alert
    Given I go to "quote" page
    And I "accept" third party agreement

  @market6_iframe
  Scenario: Additional info
    Given I go to "quote" page
    And fill out additional info with name "John Doe" and phone "1234567890"

  @market7_NewWindow
  Scenario: Related docs
    Given I go to "quote" page
    And I verify "Document 2" present on related docs page
    And I fill out optional fields

  @ecosia2
  Scenario: JavaScript click
    Given I go to "ecosia" page
    And I fill out search engine field with "BDD" and search

    @market8
    Scenario: waits on quote
      Given I go to "quote" page
      And I test waits







