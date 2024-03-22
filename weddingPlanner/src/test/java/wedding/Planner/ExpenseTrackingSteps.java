package wedding.Planner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ExpenseTrackingSteps {
    private String userRole;
    private boolean reservationMade = false;
    private String expenseCategory = "";

    @Given("User role is {string}")
    public void user_role_is(String role) {
        // Set the user role based on the scenario step
        this.userRole = role;
    }

    @When("User Makes a Reservation")
    public void user_makes_a_reservation() {
        // Simulate making a reservation
        // In a real application, this would involve more complex logic
        this.reservationMade = true;
    }

    @Then("User Expense Should Be Tracked Under Hall Reservation Category")
    public void user_expense_should_be_tracked_under_hall_reservation_category() {
        // Check if a reservation was made and set the expense category accordingly
        if (reservationMade) {
            this.expenseCategory = "Hall Reservation";
            System.out.println("Expense tracked under: " + this.expenseCategory);
        } else {
            System.out.println("No reservation made, no expense tracked.");
        }
    }
}
