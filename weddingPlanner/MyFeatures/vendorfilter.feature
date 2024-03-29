Feature: Vendor Search and Filtering
  Scenario: Filter vendors by a single criterion
    Given there are vendors with distinguishable attributes
    When the user applies a filter criterion
    Then the system displays vendors matching the criterion

  Scenario: Filter vendors by multiple criteria
    Given there are vendors with various attributes
    When the user applies multiple filter criteria
    Then the system displays vendors matching all criteria

  Scenario: No vendors match the filter criteria
    Given there are vendors listed in the system
    When the user applies a filter that matches no vendors
    Then the system displays a message indicating no matches