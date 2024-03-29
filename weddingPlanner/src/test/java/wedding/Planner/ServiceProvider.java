package wedding.Planner;

public class ServiceProvider extends User {
    private String serviceType;
    private String location;
    private double pricing;
    private double rating;

    public ServiceProvider(String username, String password, String hallnumber, String serviceType, String location, double pricing, double rating) {
        super(username, password, "SERVICE_PROVIDER", hallnumber);
        this.serviceType = serviceType;
        this.location = location;
        this.pricing = pricing;
        this.rating = rating;
    }

    // Getters
    public String getServiceType() {
        return serviceType;
    }

    public String getLocation() {
        return location;
    }

    public double getPricing() {
        return pricing;
    }

    public double getRating() {
        return rating;
    }

    // Setters
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPricing(double pricing) {
        this.pricing = pricing;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
