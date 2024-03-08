package wedding.Planner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.*;

public class VenueService {
    private Map<String, Venue> venues = new HashMap<>();

    public VenueService() {
        // Initialize with a venue that can be booked
        venues.put("Venue1", new Venue("Venue1", "2024-5-5", 100, true));
        venues.put("Venue2", new Venue("Venue2", "2024-6-6", 200, true));
        venues.put("Venue3", new Venue("Venue3", "2024-7-7", 300, true));// Assume Venue constructor accepts an availability flag
    }

    public Venue findVenue(String venueId) {
        return venues.get(venueId);
    }

    public Booking bookVenue(Venue venue, String date) {
        // Add logic to handle booking only if the venue is available on the given date
        // For simplicity, let's assume all dates are available for this venue
        return new Booking(venue, date);
    }

    // ... other methods ...
}



