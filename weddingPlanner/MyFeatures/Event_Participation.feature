Feature: Event Participation
  Scenario: User Paticipation Success
    Given user is in the ACTIVE EVENTS page
    And the user isn't the owner of the ACTIVE EVENT
    When user clicks participate
    Then user should be presented with this Success "Participation Successful" Message

  Scenario: User Paticipation Failed
    Given user is in the ACTIVE EVENTS page
    And the user is the owner of the ACTIVE EVENT
    When user clicks participate
    Then user should be presented with this Failure "Participation Failed" Message
