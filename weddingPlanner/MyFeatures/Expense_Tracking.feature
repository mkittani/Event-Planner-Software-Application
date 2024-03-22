Feature: Expense Tracking
  Background:
    Scenario: User Makes a Reservation
    Given User role is "USER"
    When User Makes a Reservation
    Then User Expense Should Be Tracked Under Hall Reservation Category