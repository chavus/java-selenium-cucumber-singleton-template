@properties
Feature: Properties Handling

  Scenario: User can get mandatory property from properties file
    Given I have a property file test.properties
    Then I can print the value of property: baseUrl

  Scenario: User can get default property from properties file
    Given I have a property file test.properties
    Then I can get value of a non mandatory property