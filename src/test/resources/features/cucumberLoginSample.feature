@login
Feature: Cucumber Login

  Scenario Outline: Enter incorrect credentials
    Given I go to the Cucumber main page
    And I go to Login page
    When I enter email:<username> and password:<password>
    Then  <errorMessage> message is displayed
    Examples:
    | username | password  | errorMessage |
    | "anemail@gmail.com" | "apassword" | "Invalid email or password." |
    | "another@mail.com"   | ""          | "Invalid email or password." |




