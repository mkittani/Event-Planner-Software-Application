package wedding.Planner;
import java.util.logging.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AccessSteps {
    private static final Logger logger = Logger.getLogger(AccessSteps.class.getName());

    boolean userHasActiveEvent = false;
    boolean isAdmin = false;

    @Given("the user is in the MENU page")
    public void the_user_is_in_the_menu_page() {
        logger.info("inside the user is in the MENU page");
    }
    @Given("the user has an active event")
    public void the_user_has_an_active_event() {
        userHasActiveEvent = true;
    }
    @When("user clicks at Manage")
    public void user_clicks_at_manage() {
        if (userHasActiveEvent) {
            // Redirect user to the event managing page
            logger.info("Redirecting user to the event managing page");
        } else {
            Assert.fail("User does not have an active event");
        }
    }
    @Then("user should be redirected to the event managing page")
    public void user_should_be_redirected_to_the_event_managing_page() {
    }

//--------------------------------------------Scenario 2-----------------------------------------------------------
    @Given("the user doesn't have an active event")
    public void the_user_doesn_t_have_an_active_event() {
        userHasActiveEvent = false;
    }
    @When("user clicks at ACTIVE EVENTS")
    public void user_clicks_at_active_events() {
        if (!userHasActiveEvent) {
            logger.info("User doesn't have an active event");
        }
    }
    @Then("user should be presented with {string} Message")
    public void user_should_be_presented_with_message(String message) {
        Assert.assertEquals("You don't have an active event", message);
    }

//--------------------------------------------Scenario 3-----------------------------------------------------------
    @Given("The admin is in the MENU page")
    public void the_admin_is_in_the_menu_page() {
        // Simulating admin logged in
        isAdmin = true;
        logger.info("Admin is in the MENU page");
    }
    @When("admin clicks at ACTIVE EVENTS")
    public void admin_clicks_at_active_events() {
        // Handling admin clicking at ACTIVE EVENTS
        if (isAdmin) {
            logger.info("Redirecting admin to the event managing page");
            // Assuming admin is redirected to the event managing page
        } else {
            Assert.fail("Only admin can access this feature");
        }
    }
    @Then("admin should be redirected to the event managing page")
    public void admin_should_be_redirected_to_the_event_managing_page() {
    }
    @Then("admin should see all ACTIVE EVENTS")
    public void admin_should_see_all_active_events() {
        // Asserting that admin can see all active events
        logger.info("Admin can see all active events");
    }
    @Then("admin should be able to manage any ACTIVE EVENT")
    public void admin_should_be_able_to_manage_any_active_event() {
        // Asserting that admin can manage any active event
        logger.info("Admin can manage any active event");
    }
}