package wedding.Planner;
import static org.junit.Assert.assertEquals;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.logging.Logger;


public class Login {
    private static final Logger logger = Logger.getLogger(AccessSteps.class.getName());

    private UserManager userManager;
    private String currentUser; // This should be role, not specific user
    private boolean loginSuccess;

    public Login() {
        userManager = new UserManager();
        userManager.registerUser("adminUser", "adminPass", "ADMIN"," hallnumber");
        userManager.registerUser("serviceUser", "servicePass", "SERVICE_PROVIDER","hall  number");
        userManager.registerUser("regularUser", "userPass", "USER","hall number");
    }

    @Given("the {string} is on the login page")
    public void the_role_is_on_the_login_page(String role) {
        currentUser = role; // 'role' should be 'Admin', 'User', or 'ServiceProvider'
        loginSuccess = false; // Reset login success flag
    }

    @When("the {string} enters {string} and {string}")
    public void the_role_enters(String role, String username, String password) {
        User loggedInUser = userManager.loginUser(username, password);
        loginSuccess = (loggedInUser != null); // True if login is successful (i.e., loggedInUser is not null)
        if (loginSuccess) {
            currentUser = String.valueOf(loggedInUser); // Set the currentUser to the loggedInUser if login is successful
            assertEquals(role.toUpperCase(), currentUser.getBytes().toString()); // Optionally, assert that the logged-in user has the expected role
        }
    }

    @Then("the {string} is logged in")
    public void the_role_is_logged_in(String role) {
        assert currentUser.equals(role) && loginSuccess;
    }

    @Then("{string} menu is displayed")
    public void menu_is_displayed(String role) {
        if (loginSuccess) {
            logger.info(role + " menu is displayed.");
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
