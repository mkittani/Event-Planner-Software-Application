package wedding.Planner;


import wedding.Planner.Venue;
import wedding.Planner.Booking;
import wedding.Planner.VenueService;
import javax.swing.*;
import java.util.List;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;



public class  VenueBookingSteps {

    private VenueService venueService;
    private Venue selectedVenue;
    private Booking currentBooking;


    public VenueBookingSteps() {
        this.venueService = new VenueService(); // Make sure this is the same instance used throughout the application
    }
    public void selectVenue(String venueId) {
        Venue venue = venueService.findVenue(venueId);
        if (venue == null) {
            throw new IllegalStateException("Venue with ID: " + venueId + " not found.");
        }
        this.selectedVenue = venue;
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
        // Your reservation logic...
        JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true); // This makes the dialog always on top

        if (selectedVenue == null || !venueService.isAvailable(selectedVenue.getId(), date)) {
            JOptionPane.showMessageDialog(dialog, "Venue is not available on the selected date.", "Booking Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Booking booking = venueService.bookVenue(selectedVenue.getId(), date);
        if (booking == null) {
            JOptionPane.showMessageDialog(dialog, "Failed to book the venue for the selected date.", "Booking Error", JOptionPane.ERROR_MESSAGE);
        } else {
            currentBooking = booking;
            String message = "Booking confirmed for venue: " + selectedVenue.getId() + " on date: " + date;
            JOptionPane.showMessageDialog(dialog, message, "Booking Confirmation", JOptionPane.INFORMATION_MESSAGE);
        }
        dialog.dispose(); // Dispose of the dialog after the message is displayed
    }

    // Public method to confirm the reservation
    public void confirmTheReservation() {
        if (currentBooking == null || currentBooking.getVenue() == null) {
            throw new IllegalStateException("Booking or venue is null, cannot confirm reservation.");
        }

        System.out.println("Booking confirmed for venue: " + currentBooking.getVenue().getId());
    }
    public void cancelReservation(String venueId) {
        if (venueService.cancelReservation(venueId)) {
            System.out.println("Reservation cancelled for venue: " + venueId);
            if (currentBooking != null && currentBooking.getVenue().getId().equals(venueId)) {
                currentBooking = null;
            }
        } else {
            throw new IllegalStateException("Failed to cancel the reservation for venue: " + venueId);
        }
    }
}
