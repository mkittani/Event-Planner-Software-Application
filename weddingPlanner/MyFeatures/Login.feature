Feature: Login

  Scenario: Successful login as Admin
    Given the "Admin" is on the login page
    When the "Admin" enters "adminUser" and "adminPass"
    Then the "Admin" is logged in
    And "Admin" menu is displayed

  Scenario: Unsuccessful login as Admin
    Given the "Admin" is on the login page
    When the "Admin" enters "adminUser" and "wrongPassword"
    Then the "Admin" is not logged in
    And "Admin" menu is not displayed

  Scenario: Successful login as Service Provider
    Given the "ServiceProvider" is on the login page
    When the "ServiceProvider" enters "serviceUser" and "servicePass"
    Then the "ServiceProvider" is logged in
    And "ServiceProvider" menu is displayed

  Scenario: Unsuccessful login as Service Provider
    Given the "Service Provider" is on the login page
    When the "Service Provider" enters "serviceUser" and "wrongPassword"
    Then the "Service Provider" is not logged in
    And "Service Provider" menu is not displayed

  Scenario: Successful login as User
    Given the "User" is on the login page
    When the "User" enters "serviceUser" and "userPass"
    Then the "User" is logged in
    And "User" menu is displayed


  Scenario: Unsuccessful login as User
    Given the "User" is on the login page
    When the "User" enters "regularUser" and "wrongPass"
    Then the "User" is not logged in
    And "User" menu is not displayed
