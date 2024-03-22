package wedding.Planner;
import java.util.*;

import static java.lang.System.*;

public class UserManager {
//    private static User info;
//    private static RegularUser info;
    private Map<String, User> users = new HashMap<>();
    private static User user;

    private EventMediaManager mediaManager = new EventMediaManager(); // Media manager instance

    public User getUserById(String username) {
        return users.get(username); // This will return the user associated with the username, or null if no such user exists
    }
    //--------------------------------------------------------------------------------------------------------------------------
    public void addMediaToUserEvent(String username, Media media) {
        User user = users.get(username);
        if (user != null && user.getHallnumber() != null) {
            mediaManager.addMediaToEvent(user.getHallnumber(), media);
            System.out.println("Media added to event.");
        } else {
            System.out.println("User does not have an active event to add media.");
        }
    }
    public List<Media> getMediaForUserEvent(String username) {
        User user = users.get(username);
        if (user != null && user.getHallnumber() != null) {
            return mediaManager.getMediaForEvent(user.getHallnumber());
        } else {
            System.out.println("User does not have an active event.");
            return null;
        }
    }
    public void removeMediaFromUserEvent(String username, Media media) {
        User user = users.get(username);
        if (user != null && user.getHallnumber() != null) {
            mediaManager.removeMediaFromEvent(user.getHallnumber(), media);
            System.out.println("Media removed from event.");
        } else {
            System.out.println("User does not have an active event.");
        }
    }
    //--------------------------------------------------------------------------------------------------------------------------


    public void registerUser(String username, String password, String role, String hallnumber) {
        User user;
        switch (role.toUpperCase()) {
            case "ADMIN":
                user = new Admin(username, password, hallnumber);
                break;
            case "SERVICE_PROVIDER":
                user = new ServiceProvider(username, password, hallnumber);
                break;
            case "USER":
            default:
                user = new RegularUser(username, password, hallnumber);
                break;
        }
        users.put(username, user); // Store the user
    }
    public void printActiveEvents() {
        boolean hasActiveEvents = false;
        System.out.println("Active Events:");
        for (Map.Entry<String, User> entry : this.users.entrySet()) {
            User user = entry.getValue();
            if (user.getHallnumber() != null && !user.getHallnumber().isEmpty()) {
                System.out.println("Username: " + entry.getKey() + " - Event Hall: " + user.getHallnumber());
                hasActiveEvents = true;
            }
        }
        if (!hasActiveEvents) {
            System.out.println("No active events at the moment.");
        }
    }

    public boolean loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) { // Password should be checked with a hashed value in real scenarios
            System.out.println("Login successful for " + user.getRole() + ": " + username +" " + user.getHallnumber());
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        VenueBookingSteps venueBookingSteps = new VenueBookingSteps();

        // Register some users
        userManager.registerUser("adminUser", "adminPass", "ADMIN","hallnumber");
        userManager.registerUser("serviceProviderUser", "servicePass", "SERVICE_PROVIDER","hallnumber");
        userManager.registerUser("regularUser", "userPass", "USER","hallnumber");

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            out.println("Welcome to our wedding planner application!");
            out.println("************************************************");
            out.println("1-Sign in");
            out.println("2-Sign up");
            out.println("3-bookingvenue");
           // out.println("3-Sign up");
            out.println("4-Exit");

            out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume the leftover newline

            switch (choice) {
                case 1: // Sign in
                    out.print("Enter username: ");
                    String username = sc.nextLine();
                    out.print("Enter password: ");
                    String password = sc.nextLine();
                    boolean success = userManager.loginUser(username, password);
//--------------------------------------------------------------------------------------------------------------------------
                    //UserManager userManager2 = new UserManager();
                    if(success) {
                        user = userManager.getUserById(username);
                        if (user.getRole().equals("USER")) {
                            //switch statement for USER menu
                            //out.println("userrole is user");
                            out.println("1. My Active Events");
                            out.println("2. Active Events");
                            out.println("3. New Event");
                            out.println("4. Events Description");
                            //-----------------------------------Osama Salah---------------------------------------------------------------------------------------
                            out.println("5. Add Media to My Event");
                            out.println("6. View My Event Media");
                            out.println("7. Remove Media from My Event");
                            //-----------------------------------Osama Salah---------------------------------------------------------------------------------------
                            out.println("Choose an option: ");
                            int userChoice = sc.nextInt();
                            switch (userChoice) {
                                case 1: // User's Active Events Menu
                                    if (user.getHallnumber() == null)
                                        out.println("You don't have an active event");
                                    else {
                                        out.print("You have an active Event in: " + user.getHallnumber() + "    Enter 1 to Manage");
                                        int manageChoice = sc.nextInt();
                                        if (manageChoice == 1) {
                                            out.println("1. Delete Event");

                                            //--------------------Ameed's Part-------------------------------------
                                            // Void Function that sets the user.getHallnumber() to null
                                            //--------------------Ameed's Part-------------------------------------

                                            out.println("Event Deleted Successfully");
                                        }

                                    }

                                    break;

                                case 2: // All Active Events
                                    userManager.printActiveEvents();

                                    break;

                                case 3: // Reserve New Event
                                    out.println("1. Hall1:\nDate: 15/5/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Nablus\nPeople: 300\nTheme: Dark Grey\nDescription: " +
                                            "Contains fans, each table takes up to 5 people, Price: 2500 ils\n");
                                    out.println("2. Hall2:\nDate: 25/5/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Tulkarm\nPeople: 400\nTheme: Off white\nDescription: " +
                                            "Contains air conditioning, each table takes up to 10 people, Price: 3500 ils\n");
                                    out.println("3. Hall3:\nDate: 15/6/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Jenin\nPeople: 500\nTheme: Sky Blue\nDescription: " +
                                            "Contains air conditioning, each table takes up to 15 people, Price: 4500 ils\n");
                                    out.println("4. Hall4:\nDate: 25/6/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Kalkelye\nPeople: 600\nTheme: Dark Blue\nDescription: " +
                                            "Contains air conditioning, each table takes up to 20 people, Price: 5500 ils\n");
                                    out.println("Enter a number from 1 to 4 representing the hall number:");
                                    int hallChoice = sc.nextInt(); // Read the user's hall number choice
                                    sc.nextLine(); // Consume the newline left-over

                                    // Convert the numerical choice into a hall number string
                                    String hallNumber ;
                                    switch (hallChoice) {
                                        case 1:
                                            hallNumber = "Hall1";
                                            break;
                                        case 2:
                                            hallNumber = "Hall2";
                                            break;
                                        case 3:
                                            hallNumber = "Hall3";
                                            break;
                                        case 4:
                                            hallNumber = "Hall4";
                                            break;
                                        default:
                                            out.println("Invalid hall number. Setting default to 'null'.");
                                            hallNumber = null; // Set a default value or handle this case as you see fit
                                            break;
                                    }

                                    out.println(user.getPassword());
                                    user.setHallnumber(hallNumber);
                                    out.println(user.getHallnumber());
                                    break;

                                case 4: // Events Description
                                    out.println("1. Hall1");
                                    out.println("2. Hall2");
                                    out.println("3. Hall3");
                                    out.println("4. Hall4");
                                    out.println("Choose Which Hall You Want The Description Of: ");
                                    int DesChoice = sc.nextInt();
                                    switch (DesChoice){
                                        case 1:
                                            out.println("\nDate: 15/5/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Nablus\nPeople: 300\nTheme: Dark Grey\nDescription: " +
                                                    "Contains fans, each table takes up to 5 people, Price: 2500 ils\n");
                                            break;
                                        case 2:
                                            out.println("\nDate: 25/5/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Tulkarm\nPeople: 400\nTheme: Off white\nDescription: " +
                                                    "Contains air conditioning, each table takes up to 10 people, Price: 3500 ils\n");
                                            break;
                                        case 3:
                                            out.println("\nDate: 15/6/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Jenin\nPeople: 500\nTheme: Sky Blue\nDescription: " +
                                                    "Contains air conditioning, each table takes up to 15 people, Price: 4500 ils\n");
                                            break;
                                        case 4:
                                            out.println("\nDate: 25/6/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Kalkelye\nPeople: 600\nTheme: Dark Blue\nDescription: " +
                                                    "Contains air conditioning, each table takes up to 20 people, Price: 5500 ils\n");
                                            break;
                                    }
                                    break;
                                //---------------------------Osama Salah-----------------------------------------------------------------------------------------------
                                case 5: // Add Media to Event
                                    out.println("Enter the type of media (e.g., 'image', 'video'): ");
                                    String type = sc.nextLine();
                                    out.println("Enter the URL or path to the media: ");
                                    String url = sc.nextLine();
                                    Media mediaToAdd = new Media(type, url);
                                    userManager.addMediaToUserEvent(username, mediaToAdd);
                                    break;

                                case 6: // View My Event Media
                                    List<Media> mediaList = userManager.getMediaForUserEvent(username);
                                    if (mediaList == null || mediaList.isEmpty()) {
                                        out.println("You have no media for your event.");
                                    } else {
                                        out.println("Your event media:");
                                        for (Media media : mediaList) {
                                            out.println("Type: " + media.getType() + ", URL: " + media.getUrl());
                                        }
                                    }
                                    break;

                                case 7: // Remove Media from Event
                                    out.println("Enter the URL of the media you wish to remove: ");
                                    String mediaUrl = sc.nextLine();
                                    // Assuming the media type isn't necessary for removal, adjust as needed
                                    Media mediaToRemove = new Media("", mediaUrl); // Empty type, only URL needed for this example
                                    userManager.removeMediaFromUserEvent(username, mediaToRemove);
                                    break;
                                    //-----------------------------------Osama Salah---------------------------------------------------------------------------------------

                                default:
                                    throw new IllegalStateException("Unexpected value: " + userChoice);
                            }

                        }
                        if (user.getRole().equals("ADMIN")) {
                            //switch statement for ADMIN menu
                            //out.println("userrole is admin");
                            out.println("1. Active Events");
                            out.println("2. Register New User");
                            out.println("3. Events Description");


                            out.println("Choose an option: ");
                            int userChoice = sc.nextInt();
                            switch (userChoice){
                                case 1: // All Active Events
                                    userManager.printActiveEvents();

                                    break;

                                case 2: // Register New User
                                    out.println("enter Username: ");
                                    String NewUsername = sc.nextLine();
                                    out.println("enter Password: ");
                                    String NewPassword = sc.nextLine();
                                    out.println("enter Role: ");
                                    String NewRole = sc.nextLine();
                                    out.println("enter Hall Number: ");
                                    String NewHallNumber = sc.nextLine();
                                    userManager.registerUser(NewUsername, NewPassword, NewRole, NewHallNumber); // Register the new user
                                    out.println("User registered successfully!\n");
                                    break;

                                case 3: // Events Description
                                    out.println("1. Hall1");
                                    out.println("2. Hall2");
                                    out.println("3. Hall3");
                                    out.println("4. Hall4");
                                    out.println("Choose Which Hall You Want The Description Of: ");
                                    int DesChoice = sc.nextInt();
                                    switch (DesChoice){
                                        case 1:
                                            out.println("\nDate: 15/5/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Nablus\nPeople: 300\nTheme: Dark Grey\nDescription: " +
                                                    "Contains fans, each table takes up to 5 people, Price: 2500 ils\n");
                                            break;
                                        case 2:
                                            out.println("\nDate: 25/5/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Tulkarm\nPeople: 400\nTheme: Off white\nDescription: " +
                                                    "Contains air conditioning, each table takes up to 10 people, Price: 3500 ils\n");
                                            break;
                                        case 3:
                                            out.println("\nDate: 15/6/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Jenin\nPeople: 500\nTheme: Sky Blue\nDescription: " +
                                                    "Contains air conditioning, each table takes up to 15 people, Price: 4500 ils\n");
                                            break;
                                        case 4:
                                            out.println("\nDate: 25/6/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Kalkelye\nPeople: 600\nTheme: Dark Blue\nDescription: " +
                                                    "Contains air conditioning, each table takes up to 20 people, Price: 5500 ils\n");
                                            break;
                                    }
                                    break;
                            }



                        }
                    }
//--------------------------------------------------------------------------------------------------------------------------

                    if (!success) {
                        out.println("Login failed!");
                    }
                    break;
                case 2: // Sign up
                    out.print("Enter username: ");
                    String newUsername = sc.nextLine();
                    out.print("Enter password: ");
                    String newPassword = sc.nextLine();
                    out.print("Enter role (ADMIN, SERVICE_PROVIDER, USER): ");
                    String role = sc.nextLine();


                    userManager.registerUser(newUsername, newPassword, role, null); // Register the new user
                    out.println("User registered successfully!\n");
                    // Display or do additional stuff as needed after successful registration.

                    break;
//******************************************************************************************************
                case 3: // Book a venue


                    System.out.println("Please enter the venue ID you wish to book:");
                    String venueId = sc.nextLine();

                    System.out.println("Please enter the date you wish to book the venue for (YYYY-MM-DD):");
                    String date = sc.nextLine();

                    try {
                        venueBookingSteps.findASuitableVenue(venueId); // Attempt to find the venue
                        venueBookingSteps.reserveVenueForSpecificDate(date); // Attempt to reserve the venue for the specified date

                        System.out.println("Venue booked successfully!");
                        venueBookingSteps.confirmTheReservation(); // This method should provide additional confirmation

                    } catch (IllegalStateException e) {
                        System.out.println("Booking failed: " + e.getMessage()); // Print out the error message if booking fails
                    }
                    break;
//******************************************************************************************************************
                case 4: // Exit
                    exit = true;
                    break;
                default:
                    out.println("Invalid option. Please try again.");
                    break;
            }
        }

        sc.close();

    }

}
