package wedding.Planner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {
    private UserManager userManager;
    private String currentUser; // This should be role, not specific user
    private boolean loginSuccess;

    public Login() {
        userManager = new UserManager();
        userManager.registerUser("adminUser", "adminPass", "ADMIN","hallnumber");
        userManager.registerUser("serviceUser", "servicePass", "SERVICE_PROVIDER","hallnumber");
        userManager.registerUser("regularUser", "userPass", "USER","hallnumber");
    }

    @Given("the {string} is on the login page")
    public void the_role_is_on_the_login_page(String role) {
        currentUser = role; // 'role' should be 'Admin', 'User', or 'ServiceProvider'
        loginSuccess = false; // Reset login success flag
    }

    @When("the {string} enters {string} and {string}")
    public void the_role_enters(String role, String username, String password) {
        currentUser = role;
        loginSuccess = userManager.loginUser(username, password);
    }

    @Then("the {string} is logged in")
    public void the_role_is_logged_in(String role) {
        assert currentUser.equals(role) && loginSuccess;
    }

    @Then("{string} menu is displayed")
    public void menu_is_displayed(String role) {
        if (loginSuccess) {
            System.out.println(role + " menu is displayed.");
        }
    }

    @Then("the {string} is not logged in")
    public void the_role_is_not_logged_in(String role) {
        assert currentUser.equals(role) && !loginSuccess;
    }

    @Then("{string} menu is not displayed")
    public void menu_is_not_displayed(String role) {
        if (!loginSuccess) {
            System.out.println(role + " menu is not displayed.");
        }
    }
}
