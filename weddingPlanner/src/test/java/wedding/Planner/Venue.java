package wedding.Planner;


public class Venue {
    private String id;
    private String date;
    private String location;
    private int capacity;

    // Constructor
    public Venue(String id, String date, String location, int capacity) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.capacity = capacity;
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



}
