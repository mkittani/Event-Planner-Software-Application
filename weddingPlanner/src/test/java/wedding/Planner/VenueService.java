package wedding.Planner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.*;

public class VenueService {
    private Map<String, Venue> venues = new HashMap<>();
    private Map<String, Boolean> bookings = new HashMap<>();

    public VenueService() {
        // Initialize with a venue that can be booked
        venues.put("Venue1", new Venue("Venue1", "2024-5-5", "newyork", 100));
        venues.put("Venue2", new Venue("Venue2", "2024-5-25", "florida", 200));
        venues.put("Venue3", new Venue("Venue3", "2024-5-31", "texas", 300));
        venues.put("Venue4", new Venue("Venue4", "2024-5-10", "calefornia", 400));

    }

    public void displayVenues() {
        System.out.println("Available Venues:");
        for (Map.Entry<String, Venue> entry : venues.entrySet()) {
            Venue venue = entry.getValue();
            System.out.println("ID: " + venue.getId() + ", Location: " + venue.getLocation() +
                    ", Date: " + venue.getDate() + ", Capacity: " + venue.getCapacity());
        }
    }
    public Venue findVenue(String venueId) {
        return venues.get(venueId);
    }
    public void DisplayCalender(){
        // Assuming we're focusing on May 2024 for demonstration
        int year = 2024;
        int month = 4; // May (Java Calendar months are 0-based)

        Calendar calendar = new GregorianCalendar(year, month, 1);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        System.out.println("\nCalendar for May 2024");
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");

        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        for (int i = 1; i < firstDayOfWeek; i++) {
            System.out.print("    ");
        }

        for (int day = 1, dayOfWeek = firstDayOfWeek; day <= daysInMonth; day++, dayOfWeek++) {
            if (dayOfWeek > 7) {
                dayOfWeek = 1; // Reset to Sunday
                System.out.println();
            }

            // Check if the date is booked
            final String dateToCheck = String.format("%d-%d-%d", year, month + 1, day); // Adjust month for display
            boolean isBooked = venues.values().stream()
                    .anyMatch(venue -> venue.getDate().equals(dateToCheck));

            if (isBooked) {
                System.out.printf("[%2d] ", day); // Mark booked dates with brackets
            } else {
                System.out.printf("%3d ", day);
            }        }

        System.out.println(); // New line at the end of the calendar

    }

    public boolean isAvailable(String venueId, String date) {
        Venue venue = venues.get(venueId);
        return venue != null && venue.getDate().equals(date);
    }

    public Booking bookVenue(String venueId, String date) {
        Venue venue = venues.get(venueId);
        if (venue != null && !venue.isBooked() && venue.getDate().equals(date)) {
            venue.setBooked(true); // Mark the venue as booked
            // Assuming Booking constructor accepts Venue and date, and sets them appropriately
            return new Booking(venue, date);
        }
        return null; // Venue is already booked or does not meet criteria
    }

    public boolean cancelReservation(String venueId) {
        Venue venue = venues.get(venueId);
        if (venue != null && venue.isBooked()) {
            venue.setBooked(false);
            return true;
        }
        return false;
    }


}



