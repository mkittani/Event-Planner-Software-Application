package wedding.Planner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VenueService {
    // This is a simplistic implementation. You'd typically interact with a database here.
    private List<Venue> venues = new ArrayList<>();

    public List<Venue> searchVenues(String date, String location, int capacity) {
        // Return a list of venues that match the criteria
        return venues.stream()
                .filter(venue -> venue.getDate().equals(date) && venue.getLocation().equals(location) && venue.getCapacity() >= capacity)
                .collect(Collectors.toList());
    }

    public Venue findVenue(String venueId) {
        // Find and return a venue by ID
        return venues.stream()
                .filter(venue -> venue.getId().equals(venueId))
                .findFirst()
                .orElse(null);
    }

    public Booking bookVenue(Venue venue, String date) {
        // Implement booking logic, check for availability, and create a booking
        // This is a simplified version
        Booking booking = new Booking(venue, date);
        // Add booking to a list of bookings, check for conflicts, etc.
        return booking;
    }

    public void cancelBooking(Booking booking) {
        // Cancel the booking
        // Remove booking from list, update venue availability, etc.
    }

    public boolean updateBooking(Booking booking, String newDate) {
        // Update the booking with a new date
        booking.setDate(newDate);
        // Update booking list, check for conflicts, etc.
        return false;
    }



}