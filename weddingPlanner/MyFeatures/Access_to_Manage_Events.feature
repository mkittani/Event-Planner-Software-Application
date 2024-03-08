Feature: Access to Manage Events

  Scenario: If user has an active event
    Given the user is in the MENU page
    And the user has an active event
    When user clicks at Manage
    Then user should be redirected to the event managing page


  Scenario: If user doesn't have an active event
    Given the user is in the MENU page
    And the user doesn't have an active event
    When user clicks at ACTIVE EVENTS
    Then user should be presented with "You don't have an active event" Message

  Scenario: If admin is logged in
    Given The admin is in the MENU page
    When admin clicks at ACTIVE EVENTS
    Then admin should be redirected to the event managing page
    And admin should see all ACTIVE EVENTS
    And admin should be able to manage any ACTIVE EVENT