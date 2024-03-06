package wedding.Planner;
import io.cucumber.java.en.*;
import static java.lang.System.*;
import java.util.*;

public class event_creation {
    int hall_number;
    public static void main(String[] args){
        event_creation instance = new event_creation();
        instance.an_event_organizer_is_logged_in();
        event_creation instance2 = new event_creation();
        instance2.the_organizer_creates_a_new_event();
    }

    @Given("an event organizer is logged in")
    public void an_event_organizer_is_logged_in() {
        out.println("Welcome to our wedding planner application!");
        out.println("************************************************");
        out.println("Please select a hall that suits your needs from the following menu:");
    }

    @When("the organizer creates a new event")
    public void the_organizer_creates_a_new_event() {
        out.println("1. Hall1:\nDate: 15/5/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Nablus\nPeople: 300\nTheme: Dark Grey\nDescription: " +
                "Contains fans, each table takes up to 5 people, Price: 2500 ils");
        out.println("2. Hall2:\nDate: 25/5/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Tulkarm\nPeople: 400\nTheme: Off white\nDescription: " +
                "Contains air conditioning, each table takes up to 10 people, Price: 3500 ils");
        out.println("3. Hall3:\nDate: 15/6/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Jenin\nPeople: 500\nTheme: Sky Blue\nDescription: " +
                "Contains air conditioning, each table takes up to 15 people, Price: 4500 ils");
        out.println("4. Hall4:\nDate: 25/6/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Kalkelye\nPeople: 600\nTheme: Dark Blue\nDescription: " +
                "Contains air conditioning, each table takes up to 20 people, Price: 5500 ils");
        Scanner scanner = new Scanner(in);
        hall_number = scanner.nextInt();
        switch (hall_number) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                out.println("Please select a valid number from the upper menu.");
        }
        event_creation instance3=new event_creation();
        instance3.the_event_is_created_successfully();
    }
    
    @When("specifies date, time, location, people, theme, and description")
    public void specifies_date_time_location_people_theme_and_description() {
    }

    @Then("the event is created successfully")
    public void the_event_is_created_successfully() {
        out.println("Congrats! Hall"+hall_number+" has been registered to the user ");
    }

}
