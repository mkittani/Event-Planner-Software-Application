Feature:login and sign up

Scenario: User Registration and Login
Given a user accesses the system
When the user clicks on "Register"
And fills in the required information
Then the user receives a confirmation email

  Scenario: User Registration and Login both
    Given a user accesses the system
    When the user clicks on "Register"
    And fills in the required information
    Then the user receives a confirmation email

  Scenario: User Registration and Login wrong password
    Given a user accesses the system
    When the user clicks on "Register"
    And fills in the required information
    Then the user receives a confirmation email

  Scenario: User Registration and Login wrong email
    Given a user accesses the system
    When the user clicks on "Register"
    And fills in the required information
    Then the user receives a confirmation email
