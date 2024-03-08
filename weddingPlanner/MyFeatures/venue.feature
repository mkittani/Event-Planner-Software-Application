Feature: venue Management


  Scenario: Attempt to Reserve an Already Booked Venue
    Given the organizer selects a venue
    When the organizer attempts to book a venue that is already reserved for the chosen date
    Then the system displays a message indicating the venue is unavailable

  Scenario: Cancel a Reservation
    Given the organizer has a reservation
    When the organizer cancels the reservation
    Then the system frees up the venue for the specified date and confirms the cancellation


  Scenario: Update Reservation Details
    Given the organizer has a reservation
    When the organizer updates the reservation details (e.g., date, time)
    Then the system updates the booking and confirms the changes