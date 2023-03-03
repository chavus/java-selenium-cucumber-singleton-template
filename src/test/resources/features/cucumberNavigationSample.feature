@navigation
Feature: Cucumber navigation

  Scenario: Users can view docs from navbar
    Given I go to the Cucumber main page
    When I select View Docs from navbar
    Then I am redirected to the documentation page
