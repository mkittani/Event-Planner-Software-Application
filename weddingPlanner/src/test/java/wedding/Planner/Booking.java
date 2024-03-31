package wedding.Planner;
public class Booking {
    private final Venue venue;

    public Booking(Venue venue, String date) {
        this.venue = venue;
    }
    public Venue getVenue() {
        return venue;
    }
}

