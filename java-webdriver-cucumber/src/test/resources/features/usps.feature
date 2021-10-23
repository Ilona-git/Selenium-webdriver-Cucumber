@usps
Feature: USPS test suite

  @usps1
  Scenario Outline: Validate ZIP codes
    Given I go to "usps" page
    When I go to Lookup ZIP page by address
    And I fill out "<street>" street, "<city>" city, "<state>" state
    Then I validate "<zip>" zip code exists in the result
    Examples:
      | street              | city      | state | zip   |
      | 11 Wall st          | New Yourk | NY    | 10005 |
      | 4970 El Camino Real | Los Altos | CA    | 94022 |


  @usps2
  Scenario: Validate zip code for Portnov Computer school
    Given I go to "usps" page
    When I go to Lookup ZIP page by address
    And I fill out "4970 El Camino Real" street, "Los Altos" city, "CA" state
    Then I validate "94022" zip code exists in the result

  @usps3
  Scenario: Calculate price
    Given I go to "usps" page
    When I go to calculate price
    And I select "United Kingdom (United Kingdom of Great Britan and Northen Ireland)" with postcard shape
    And I define "2" quantity
    Then I calculate the price and validate cost is $2.40


  @usps4
  Scenario: Phone number of the nearest Accountable Mail Pickup Service Post Office for Portnov Computer School
    Given I go to "usps" page
    When I go to Find a Location Page
    And I filter by "Post Offices™" Location Types, "Pickup Services" Services, "Accountable Mail" Available Services
    And I fill in "4970 El Camino Real 110" street, "Los Altos" city, "CA" state
    Then I print the phone number and validate it is "800-275-8777"


  @converter1
  Scenario Outline: Validate Fahrenheit to Celsius
    Given I go to "converter" page
    When I click on "Temperature"
    And I set "<Fahrenheit>" to "<Celsius>"
    Then I enter into From field "<Fahrenheit>" and verify "<Celsius>" result
    Examples:
      | Fahrenheit  | Celsius |
      | 54          |  129.2  |
      | 70          |  158    |
      | 100         |  212    |

  @converter2
  Scenario: Convert pound to kilogram
    Given I go to "converter" page
    When I  also click on "Weight"
    And I  also set "Pound" to "Kilogram"
    Then I also enter into From field "170" and verify "77" result

  @converter3
  Scenario: Mile to Kilometer
    Given I go to "converter" page
    When I want click on "Length"
    And I want to set "Mile" to "Kilometer"
    Then I  want  enter into From field "50" and verify "80.4" result

  @outlinePractice
  Scenario Outline: Validate various
    Given I go to "google" page
    When I click on "<tab>"
    And I verify "<tab_content>"
    Examples:
      | tab   | tab_content |
      | tab 1 | value 1     |
      | tab 2 | value 2     |
      | tab 3 | value 3     |

  @calculator1
  Scenario: Verify calculator result
    Given I go to "calculator" page
    When I navigate to "Auto Loan Calculator"
    And I clear all calculator fields
    And I calculate
    Then I verify "Please provide a positive auto price." calculator error
    Then I verify that we can see  "Please provide a positive interest value." calculator error
    And I enter "25000" price, "60" months, "4.5" interest, "5000"downpayment, "0" trade-in, "California" state, "7" percent tax, "300" fees
    And I calculate
    Then I verify monthly pay is "$372.86"


  @Usps5
  Scenario: Quadcopters delivery
    Given I go to "usps" page
    When I go to "Help" tab
    And I perform "Quadcopters delivery" help search
    Then I verify that no results of "Quadcopters delivery" available in help search

  @Usps6
  Scenario: Every door direct mail
    Given I go to "usps" page
    When I go to "Every Door Direct Mail" under "Business"
    And I search for "4970 El Camino Real, Los Altos, CA 94022"
    And I click "Show Table" on the map
    When I click "Select All" on the table
    And I close modal window
    Then I verify that summary of all rows of Cost column is equal Approximate Cost in Order Summary

  @Usps7
  Scenario:
    Given I go to "usps" page
    When I search on main page for "PO BOX"


  @Usps8
  Scenario: Verify location
    Given I go to "usps" page
    When I perform "Free Boxes" search
    And I set "Mail & Ship" in filters
    Then I also verify that "7" results found
    When I select "Priority Mail | USPS" in results
    And I click "Ship Now" button
    Then I validate that Sign In is required


  @Ups1
  Scenario: UPS end to end
    Given I go to "ups" page
    And I open Shipping menu
    And I go to Create a Shipment
    When I fill out origin shipment fields
    And I submit the shipment form
    Then I verify origin shipment fields submitted
    And I cancel the shipment form
    Then I verify shipment form is reset


   @Ups2
   Scenario: UPS end to end
     Given I go to "ups" page
     And I open Shipping menu
     And I go to Create a Shipment
     When I fill out origin shipment fields
     And I submit the shipment form
     Then I verify origin shipment fields submitted
     When I fill out destination shipment fields
     Then I verify total charges appear
     When I submit the shipment form
     And I set packaging type
     Then I verify total charges changed
     When I submit the shipment form
     And I select cheapest delivery option
     And I submit the shipment form
     And I set Saturday Delivery type
     Then I verify total charges changed
     When I submit the shipment form
     And I select Paypal payment type
     And I submit the shipment form
     Then I review all recorded details on the review page
     And I cancel the shipment form
     Then I verify shipment form is reset


  @usps15
    Scenario: Multiple windows
      Given I go to "usps" page
      And I work with multiple windows
