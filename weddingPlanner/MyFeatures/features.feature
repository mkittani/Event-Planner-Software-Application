Feature: Event Management
    Scenario: User Registration and Login
    Given a user accesses the system
    When the user clicks on "Register"
    And fills in the required information
    Then the user receives a confirmation email

    Given a registered user
    When the user logs in
    Then the user is successfully logged in

    Scenario: Event Creation
    Given an event organizer is logged in
    When the organizer creates a new event
    And specifies date, time, location, theme, and description
    Then the event is created successfully

    Given an existing event
    When the organizer edits the event details
    Then the changes are saved successfully
    