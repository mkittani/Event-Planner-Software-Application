package wedding.Planner;

import io.cucumber.java.en.*;
import java.util.*;
import java.util.stream.Collectors;

public class VendorFilterTest {

    private List<Vendor> vendors = new ArrayList<>();
    private List<Vendor> filteredVendors = new ArrayList<>();

    @Given("there are vendors with distinguishable attributes")
    public void there_are_vendors_with_distinguishable_attributes() {
        vendors.clear();
        vendors.add(new Vendor("Vendor A", "New York", "Available", 500, 4));
        vendors.add(new Vendor("Vendor B", "Los Angeles", "Unavailable", 700, 5));
        // Add more vendors as needed for testing
    }

    @When("the user applies a filter criterion")
    public void the_user_applies_a_filter_criterion() {
        // Example filter: location is New York
        filteredVendors = vendors.stream()
                .filter(vendor -> "New York".equals(vendor.getLocation()))
                .collect(Collectors.toList());
    }

    @Then("the system displays vendors matching the criterion")
    public void the_system_displays_vendors_matching_the_criterion() {
        assert(filteredVendors.size() > 0);
        for (Vendor vendor : filteredVendors) {
            assert(vendor.getLocation().equals("New York"));
        }
    }

    @Given("there are vendors with various attributes")
    public void there_are_vendors_with_various_attributes() {
        // This can be similar to the first method if you're testing with the same set
        // or you can introduce new vendors with different attributes for more comprehensive testing
    }

    @When("the user applies multiple filter criteria")
    public void the_user_applies_multiple_filter_criteria() {
        // Example: location is Los Angeles and pricing less than 800
        filteredVendors = vendors.stream()
                .filter(vendor -> "Los Angeles".equals(vendor.getLocation()) && vendor.getPricing() < 800)
                .collect(Collectors.toList());
    }

    @Then("the system displays vendors matching all criteria")
    public void the_system_displays_vendors_matching_all_criteria() {
        for (Vendor vendor : filteredVendors) {
            assert("Los Angeles".equals(vendor.getLocation()) && vendor.getPricing() < 800);
        }
    }

    @Given("there are vendors listed in the system")
    public void there_are_vendors_listed_in_the_system() {
        // This setup can be similar to the first @Given method
        // It sets up the context that there are vendors in the system
    }

    @When("the user applies a filter that matches no vendors")
    public void the_user_applies_a_filter_that_matches_no_vendors() {
        // Example: filter by a location where no vendors exist
        filteredVendors = vendors.stream()
                .filter(vendor -> "Mars".equals(vendor.getLocation()))
                .collect(Collectors.toList());
    }

    @Then("the system displays a message indicating no matches")
    public void the_system_displays_a_message_indicating_no_matches() {
        assert(filteredVendors.isEmpty());
        // In actual application code, this is where you'd check if a "no matches" message is displayed to the user
    }

    // Simple Vendor class for testing purposes
    static class Vendor {
        private String name;
        private String location;
        private String availability;
        private int pricing;
        private int rating;

        public Vendor(String name, String location, String availability, int pricing, int rating) {
            this.name = name;
            this.location = location;
            this.availability = availability;
            this.pricing = pricing;
            this.rating = rating;
        }

        // Getters
        public String getName() { return name; }
        public String getLocation() { return location; }
        public String getAvailability() { return availability; }
        public int getPricing() { return pricing; }
        public int getRating() { return rating; }
    }
}
