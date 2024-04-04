package wedding.Planner;

import io.cucumber.java.en.*;
import java.util.*;

import static org.junit.Assert.*;

public class RequestPackageEtcSteps {

    private UserManager userManager;
    private User currentUser;
    private List<Booking> userBookings;
    private boolean bookingConfirmed;

    public RequestPackageEtcSteps() {
        this.userManager = new UserManager();

    }

    @Given("the user has requested a package from the vendor")
    public void the_user_has_requested_a_package_from_the_vendor() {

        userManager.registerUser("user", "pass", "USER", "hall1");
        currentUser = userManager.getUserById("user");

    }

    @When("the user negotiates the contract terms")
    public void the_user_negotiates_the_contract_terms() {

    }

    @Then("the negotiated terms are updated in the contract")
    public void the_negotiated_terms_are_updated_in_the_contract() {

    }

    @Given("the user has negotiated contract terms with the vendor")
    public void the_user_has_negotiated_contract_terms_with_the_vendor() {

        userManager.registerUser("user", "pass", "USER", "hall1");
        currentUser = userManager.getUserById("user");

    }

    @When("the user accepts the contract")
    public void the_user_accepts_the_contract() {

    }

    @Then("the booking is confirmed with the vendor")
    public void the_booking_is_confirmed_with_the_vendor() {

        assertTrue(bookingConfirmed);
    }

    @When("the user declines the contract")
    public void the_user_declines_the_contract() {

    }

    @Then("the booking request is canceled")
    public void the_booking_request_is_canceled() {

        assertFalse(bookingConfirmed);
    }

    @Given("the user has multiple confirmed bookings")
    public void the_user_has_multiple_confirmed_bookings() {

    }

    @When("the user views their bookings")
    public void the_user_views_their_bookings() {

    }

    @Then("the system displays all confirmed bookings")
    public void the_system_displays_all_confirmed_bookings() {

    }
    @Given("the user is logged in and on the vendor's page")
    public void the_user_is_logged_in_and_on_the_vendor_s_page() {
        userManager.registerUser("user", "pass", "USER", "hall1");
        User loggedInUser = userManager.loginUser("user", "pass");
        assertNotNull(loggedInUser);

        currentUser = loggedInUser;
    }

    @When("the user requests a package from the vendor")
    public void the_user_requests_a_package_from_the_vendor() {

    }

    @Then("a package request is sent to the vendor")
    public void a_package_request_is_sent_to_the_vendor() {

    }


}
