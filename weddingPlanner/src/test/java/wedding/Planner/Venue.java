package wedding.Planner;

public class Venue {
    private String id;
    private String date;
    private String location;
    private int capacity;
    private boolean isBooked; // Tracks if the venue is booked

    // Constructor
    public Venue(String id, String date, String location, int capacity) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.capacity = capacity;
        this.isBooked = false; // Initially, the venue is not booked
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
