package wedding.Planner;


import wedding.Planner.Venue;
import wedding.Planner.Booking;
import wedding.Planner.VenueService;

import java.util.List;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;



public class VenueBookingSteps {

    private VenueService venueService;
    private Venue selectedVenue;
    private Booking currentBooking;

    public VenueBookingSteps() {
        this.venueService = new VenueService(); // Initialize the venue service here or inject it
    }

    // Public method to find a suitable venue
    public void findASuitableVenue(String venueId) {
        selectedVenue = venueService.findVenue(venueId);
        if (selectedVenue == null) {
            throw new IllegalStateException("No suitable venue found for ID: " + venueId);
        }
    }

    // Public method to reserve a venue for a specific date
    public void reserveVenueForSpecificDate(String date) {
        if (selectedVenue == null) {
            throw new IllegalStateException("Venue has not been selected");
        }
        currentBooking = venueService.bookVenue(selectedVenue, date);
        if (currentBooking == null) {
            throw new IllegalStateException("Venue could not be booked for date: " + date);
        }
    }

    // Public method to confirm the reservation
    public void confirmTheReservation() {
        if (currentBooking == null || currentBooking.getVenue() == null) {
            throw new IllegalStateException("Booking or venue is null, cannot confirm reservation.");
        }
        System.out.println("Booking confirmed for venue: " + currentBooking.getVenue().getId());
    }


}
