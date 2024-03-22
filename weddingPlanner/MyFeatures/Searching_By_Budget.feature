Feature: Searching By Budget
  Background:
    Scenario: There's Packages available For the User's Budget
    Given User in the Searching By Budget Page And There's Packages Available For his Budget
    When User Enters his Budget Amount
    Then User Should Be Able To See The Available Packages Below Or Equal To his Budget Amount

    Scenario: There's No Packages available For the User's Budget
    Given User in the Searching By Budget Page And There's No Packages Available For his Budget
    When User Enters his Budget Amount
    Then User Should Be Presented With This Message "There's No Available Packages For This Budget"