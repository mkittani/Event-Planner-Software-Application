package wedding.Planner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ParticipationSteps {
    private boolean isUserOwner;

    @Given("user is in the ACTIVE EVENTS page")
    public void user_is_in_the_active_events_page() {

    }

    @Given("the user isn't the owner of the ACTIVE EVENT")
    public void the_user_isn_t_the_owner_of_the_active_event() {
        isUserOwner = false;
    }

    @When("user clicks participate")
    public void user_clicks_participate() {

    }

    @Then("user should be presented with this Success {string} Message")
    public void user_should_be_presented_with_this_success_message(String message) {
        if (!isUserOwner) {

            System.out.println("Success Message: " + message);
        }
    }

    @Given("the user is the owner of the ACTIVE EVENT")
    public void the_user_is_the_owner_of_the_active_event() {
        isUserOwner = true;
    }

    @Then("user should be presented with this Failure {string} Message")
    public void user_should_be_presented_with_this_failure_message(String message) {
        if (isUserOwner) {

            System.out.println("Failure Message: " + message);
        }
    }
}
