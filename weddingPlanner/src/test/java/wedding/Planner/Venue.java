package wedding.Planner;


public class Venue {
    private String id;
    private String date;
    private String location;
    private boolean capacity;

    // Constructor
    public Venue(String id, String date, int location, boolean capacity) {
        this.id = id;
        this.date = date;
        this.location = String.valueOf(location);
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

    public boolean getCapacity() {
        return capacity;
    }



}
