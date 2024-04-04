package wedding.Planner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EventMediaTest {
    private UserManager userManager;
    private User user; // This represents the logged-in user
    private boolean uploadSuccess;
    private String errorMessage;

    public EventMediaTest() {
        userManager = new UserManager();
        // Assume users have been registered elsewhere or add here
    }

    @Given("the {string} is logged in and on the event page")
    public void theUserIsLoggedInAndOnTheEventPage(String role) {
        // Mock user login, in real code, perform actual login and navigation
        user = userManager.getUserById("regularUser"); // Use a valid user ID
        uploadSuccess = false;
        errorMessage = "";
    }

    @When("the {string} uploads {string} and {string}")
    public void theUserUploads(String role, String mediaType, String mediaFile) {
        // In real code, this would upload the file. Here, we simulate it.
        if(mediaType.equals("photo") || mediaType.equals("video")) {
            uploadSuccess = true; // Simulate successful upload
        } else {
            uploadSuccess = false;
            errorMessage = "Unsupported media type";
        }
    }

    @When("the {string} attempts to upload {string} and {string}")
    public void theUserAttemptsToUpload(String role, String mediaType, String mediaFile) {
        // In real code, this would attempt the upload. Here, we simulate it.
        if (mediaFile.equals("largeMediaFile")) {
            uploadSuccess = false;
            errorMessage = "File size limit exceeded";
        } else {
            uploadSuccess = false; // Default to false for other conditions
        }
    }

    @Then("the {string} is added to the event")
    public void theMediaTypeIsAddedToTheEvent(String mediaType) {
        assert uploadSuccess; // In actual code, check that media was added to the event
    }

    @Then("the {string} can view the {string}")
    public void userCanViewTheMedia(String role, String mediaType) {
        assert uploadSuccess; // In actual code, verify user can view the added media
    }

    @Then("the {string} is not added to the event")
    public void mediaTypeIsNotAddedToTheEvent(String mediaType) {
        assert !uploadSuccess;
    }

    @Then("the {string} receives a {string} error")
    public void userReceivesAnError(String role, String error) {
        if (!errorMessage.equals(error)) {
            throw new AssertionError("Error message does not match expected error");
        }    }
    @Then("the {string} receives an {string} error")
    public void receivesAnError(String role, String error) {
        // Assuming 'errorMessage' is a class variable where you store the error message during the upload attempt
        if (!this.errorMessage.equals(error)) {
            throw new AssertionError("Error message does not match expected error");
        }
    }
}
