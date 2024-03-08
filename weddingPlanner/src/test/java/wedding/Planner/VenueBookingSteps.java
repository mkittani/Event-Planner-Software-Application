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
        this.venueService = new VenueService(); // Make sure this is the same instance used throughout the application
    }

    public void findASuitableVenue(String venueId) {
        Venue venue = venueService.findVenue(venueId);
        if (venue == null) {
            throw new IllegalStateException("No suitable venue found for ID: " + venueId);
        }
        // If the venue is found, store it for booking
        this.selectedVenue = venue;
    }

    public void reserveVenueForSpecificDate(String date) {
        if (this.selectedVenue == null) {
            throw new IllegalStateException("Venue has not been selected");
        }
        Booking booking = venueService.bookVenue(this.selectedVenue, date);
        if (booking == null) {
            throw new IllegalStateException("Venue could not be booked for date: " + date);
        }
        // If the booking is successful, store the booking
        this.currentBooking = booking;
    }

    // Public method to confirm the reservation
    public void confirmTheReservation() {
        if (currentBooking == null || currentBooking.getVenue() == null) {
            throw new IllegalStateException("Booking or venue is null, cannot confirm reservation.");
        }
        System.out.println("Booking confirmed for venue: " + currentBooking.getVenue().getId());
    }

}
