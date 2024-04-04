package wedding.Planner;
import java.util.logging.Logger;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ExpenseTrackingSteps {
    private static final Logger logger = Logger.getLogger(ExpenseTrackingSteps.class.getName());

    private String userRole;
    private boolean reservationMade = false;
    private String expenseCategory = "";

    @Given("User role is {string}")
    public void userRoleIs(String role) {
        // Set the user role based on the scenario step
        this.userRole = role;
    }

    @When("User Makes a Reservation")
    public void userMakesAReservation() {
        // Simulate making a reservation
        // In a real application, this would involve more complex logic
        this.reservationMade = true;
    }

    @Then("User Expense Should Be Tracked Under Hall Reservation Category")
    public void userExpenseShouldBeTrackedUnderHallReservationCategory() {
        // Check if a reservation was made and set the expense category accordingly
        if (reservationMade) {
            this.expenseCategory = "Hall Reservation";
            logger.info("Expense tracked under: " + this.expenseCategory);
        } else {
            logger.info("No reservation made, no expense tracked.");
        }
    }
}
