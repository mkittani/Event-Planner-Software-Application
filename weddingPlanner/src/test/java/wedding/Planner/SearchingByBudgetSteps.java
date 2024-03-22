package wedding.Planner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class SearchingByBudgetSteps {
    private PackageList packageList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Given("^User in the Searching By Budget Page And There's Packages Available For his Budget$")
    public void user_in_the_searching_by_budget_page_and_there_s_packages_available_for_his_budget() {
        // Initialize package list and add packages
        packageList = new PackageList();
        // Adding specified packages
        packageList.addPackage(new Package("Hall Only", 500));
        packageList.addPackage(new Package("Hall With Chief", 650));
        packageList.addPackage(new Package("Hall With DJ", 570));
        packageList.addPackage(new Package("Hall With DJ and Chief", 700));

        // Capture System.out.println output
        System.setOut(new PrintStream(outContent));
    }

    @Given("^User in the Searching By Budget Page And There's No Packages Available For his Budget$")
    public void user_in_the_searching_by_budget_page_and_there_s_no_packages_available_for_his_budget() {
        // Initialize package list without adding packages
        packageList = new PackageList();

        // Adding a package that is out of the expected budget range for testing
        packageList.addPackage(new Package("Ultra Luxury", 10000));

        // Capture System.out.println output
        System.setOut(new PrintStream(outContent));
    }

    @When("User Enters his Budget Amount")
    public void user_enters_his_budget_amount() {
        // Perform search below cost
        packageList.searchBelowCost(700);
    }

    @Then("^User Should Be Able To See The Available Packages Below Or Equal To his Budget Amount$")
    public void user_should_be_able_to_see_the_available_packages_below_or_equal_to_his_budget_amount() {
        // Assert that the output contains package details
        assertTrue("Expected to find packages, but none were found.", outContent.toString().contains("Package =>"));
    }

    @Then("^User Should Be Presented With This Message \"([^\"]*)\"$")
    public void user_should_be_presented_with_this_message(String message) {
        // Assert that the specific message is displayed
        assertTrue("Expected message was not displayed.", outContent.toString().contains(message));
    }
}
