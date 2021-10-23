@careers
Feature: Careers scenarios

  @careers1
  Scenario: Recruiter removes position
    Given I open "careers" page
    And I login as "recruiter"
    Then I verify "recruiter" login
    When I remove "Principal Automation Engineer" position
    And I verify "Principal Auomation Engineer" position is removed

  @careers2 @delete_position_if_failed
  Scenario: Recruiter creates position
    Given I open "careers" page
    And I login as "recruiter"
    Then I verify "recruiter" login
    When I create new position
    Then I verify new position is created
    When I remove new position
    And I verify new position is removed

  @careers3 @create_position @delete_position
  Scenario: Recruiter updates position
    Given I open "careers" page
    And I login as "recruiter"
    # pre-create new position for the test
    When I update new position
    Then I verify new position is updated
    # cleanup after scenario complete

  @careers4
  Scenario: Candidate creates profile
    Given I open "careers" page
    And I submit application to a new position
    Then I verify new candidate is created
    When I delete candidate profile
    Then I verify new candidate is removed

  @careers5
  Scenario: Candidate updates profile
    Given I open "careers" page
    And I submit application to a new position
    Then I verify new candidate is created
    When I update new candidate
    Then I verify new candidate is updated
    When I delete candidate profile
    Then I verify new candidate is removed

  @careers6
  Scenario: Candidates applies and withdraws from a new position
    Given I open "careers" page
    And I login as "candidate"
    Then I verify "candidate" login
    When I apply for a new position
    Then I see new position marked as applied
    And I see new position in my jobs
    When I withdraw from new position
    Then I don't see new position in my jobs

  @careers7
  Scenario: Position cascading calls
    Given I open "careers" page
    And I use cascading calls