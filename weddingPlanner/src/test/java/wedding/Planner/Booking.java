package wedding.Planner;
import wedding.Planner.Venue;
public class Booking {
    private Venue venue;
    private String date;

    // Constructor
    public Booking(Venue venue, String date) {
        this.venue = venue;
        this.date = date;
    }

    public Booking() {

    }

    // Getters and Setters
    public Venue getVenue() {
        return venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}

