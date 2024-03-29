Feature: Request Packages, Negotiate Contracts, and Manage Bookings
  Scenario: Requesting a package from a vendor
    Given the user is logged in and on the vendor's page
    When the user requests a package from the vendor
    Then a package request is sent to the vendor

  Scenario: Negotiating contract terms with a vendor
    Given the user has requested a package from the vendor
    When the user negotiates the contract terms
    Then the negotiated terms are updated in the contract

  Scenario: Accepting a contract with a vendor
    Given the user has negotiated contract terms with the vendor
    When the user accepts the contract
    Then the booking is confirmed with the vendor

  Scenario: Declining a contract with a vendor
    Given the user has negotiated contract terms with the vendor
    When the user declines the contract
    Then the booking request is canceled

  Scenario: Managing multiple vendor bookings
    Given the user has multiple confirmed bookings
    When the user views their bookings
    Then the system displays all confirmed bookings