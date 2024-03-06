Feature: add event

  Scenario: Event Creation
    Given an event organizer is logged in
    When the organizer creates a new event
    And specifies date, time, location, theme, and description
    Then the event is created successfully