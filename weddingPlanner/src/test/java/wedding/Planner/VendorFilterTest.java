package wedding.Planner;

import io.cucumber.java.en.*;
import java.util.*;
import java.util.stream.Collectors;

public class VendorFilterTest {

    private final List<Vendor> vendors = new ArrayList<>();
    private List<Vendor> filteredVendors = new ArrayList<>();

    @Given("there are vendors with distinguishable attributes")
    public void there_are_vendors_with_distinguishable_attributes() {
        vendors.clear();
        vendors.add(new Vendor("Vendor A", "New York", "Available", 500, 4));
        vendors.add(new Vendor("Vendor B", "Los Angeles", "Unavailable", 700, 5));
    }

    @When("the user applies a filter criterion")
    public void the_user_applies_a_filter_criterion() {
        filteredVendors = vendors.stream()
                .filter(vendor -> "New York".equals(vendor.getLocation()))
                .collect(Collectors.toList());
    }

    @Then("the system displays vendors matching the criterion")
    public void the_system_displays_vendors_matching_the_criterion() {
        assert(!filteredVendors.isEmpty());
        for (Vendor vendor : filteredVendors) {
            assert(vendor.getLocation().equals("New York"));
        }
    }

    @Given("there are vendors with various attributes")
    public void there_are_vendors_with_various_attributes() {
    }

    @When("the user applies multiple filter criteria")
    public void the_user_applies_multiple_filter_criteria() {
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
    }

    @When("the user applies a filter that matches no vendors")
    public void the_user_applies_a_filter_that_matches_no_vendors() {
        filteredVendors = vendors.stream()
                .filter(vendor -> "Mars".equals(vendor.getLocation()))
                .collect(Collectors.toList());
    }

    @Then("the system displays a message indicating no matches")
    public void the_system_displays_a_message_indicating_no_matches() {
        assert(filteredVendors.isEmpty());
    }

    static class Vendor {
        private final String name;
        private final String location;
        private final String availability;
        private final int pricing;

        public Vendor(String name, String location, String availability, int pricing, int rating) {
            this.name = name;
            this.location = location;
            this.availability = availability;
            this.pricing = pricing;
        }
        public String getLocation() { return location; }
        public int getPricing() { return pricing; }
    }
}
