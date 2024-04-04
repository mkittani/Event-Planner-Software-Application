package wedding.Planner;
import java.util.logging.Logger;
import java.util.*;
import java.util.stream.*;
import static java.lang.System.*;
public class UserManager {
    private static final Logger logger = Logger.getLogger(UserManager.class.getName());
    private Map<String, User> users = new HashMap<>();
    private Map<String, List<ServiceProvider>> serviceProviders = new HashMap<>();
    private EventMediaManager mediaManager = new EventMediaManager(); // Media manager instance

    private static User user;
    private static final PackageList list = new PackageList();

    public User getUserById(String username) {
        return users.get(username);
    }
    public void registerUser(String username, String password, String role, String hallnumber) {
        // Trim the role to remove leading or trailing spaces before comparison
        role = role.trim();

        User newUser;
        if ("ADMIN".equalsIgnoreCase(role)) {
            newUser = new Admin(username, password, hallnumber);
        } else {
            newUser =  new RegularUser(username, password, hallnumber);
        }
        users.put(username, newUser);
        logger.info(role + " registered successfully.");
    }
    public void registerUser(String username, String password,  String hallNumber, String serviceType, String location, double pricing, double rating) {
        hallNumber = (hallNumber == null || hallNumber.equalsIgnoreCase("none")) ? "" : hallNumber;
        ServiceProvider serviceProvider = new ServiceProvider(username, password, hallNumber, serviceType, location, pricing, rating);
        users.put(username, serviceProvider);
        serviceProviders.computeIfAbsent(serviceType.toLowerCase(), k -> new ArrayList<>()).add(serviceProvider); // Add to service providers
        logger.info("Service provider registered successfully.");
    }
    public void printActiveEvents() {
        boolean hasActiveEvents = false;
        logger.info("Active Events:");
        for (Map.Entry<String, User> entry : this.users.entrySet()) {
            User user  = entry.getValue();
            if (user.getHallnumber() != null && !user.getHallnumber().isEmpty()) {
                logger.info("Username: " + entry.getKey() + " - Event Hall: " + user.getHallnumber());
                hasActiveEvents = true;
            }
        }
        if (!hasActiveEvents) {
            logger.info("No active events at the moment.");
        }
    }

    public User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            logger.info("Login successful for " + user.getRole() + ": " + username + " " + user.getHallnumber());
            return user; // Return the user object on successful authentication
        }
        return null; // Return null if authentication fails
    }

    public void addMediaToUserEvent(String username, Media media) {
        User user  = users.get(username);
        if (user  != null && user.getHallnumber() != null) {
            mediaManager.addMediaToEvent(user.getHallnumber(), media);
            logger.info("Media added to event.");
        } else {
            logger.info("User does not have an active event to add media.");
        }
    }
    public List<Media> getMediaForUserEvent(String username) {
        User user  = users.get(username);
        if (user  != null && user.getHallnumber() != null) {
            return mediaManager.getMediaForEvent(user.getHallnumber());
        } else {
            logger.info("User does not have an active event.");
            return Collections.emptyList();
        }
    }
    public void removeMediaFromUserEvent(String username, Media media) {
        User user  = users.get(username);
        if (user  != null && user.getHallnumber() != null) {
            mediaManager.removeMediaFromEvent(user.getHallnumber(), media);
            logger.info("Media removed from event.");
        } else {
            logger.info("User does not have an active event.");
        }
    }

    public List<ServiceProvider> searchServiceProviders(String type, String location, double maxPricing, double minRating) {
        logger.info("Searching for: Type=" + type + ", Location=" + location + ", Max Pricing=" + maxPricing + ", Min Rating=" + minRating);
        return serviceProviders.getOrDefault(type.toLowerCase(), Collections.emptyList()).stream()
                .filter(provider -> provider.getLocation().equalsIgnoreCase(location))
                .filter(provider -> provider.getPricing() <= maxPricing)
                .filter(provider -> provider.getRating() >= minRating)
                .collect(Collectors.toList());
    }
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        VenueBookingSteps venueBookingSteps = new VenueBookingSteps();
        VenueService  venueservice = new VenueService();
        ExpenseManager exManager = new ExpenseManager();
        String date;

        list.addPackage(new Package("Hall Only",500));
        list.addPackage(new Package("Hall With Chief",650));
        list.addPackage(new Package("Hall With DJ",570));
        list.addPackage(new Package("Hall With DJ and Chief",700));
        userManager.registerUser("adminUser", "adminPass", "ADMIN"," hallnumber");
        userManager.registerUser("serviceProviderUser", "servicePass", "SERVICE_PROVIDER","hallnumber ");
        userManager.registerUser("regularUser", "userPass", "USER","hall number");

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            out.println("Welcome to our wedding planner application!");
            out.println("************************************************");
            out.println("1-Sign in");
            out.println("2-Sign up");
            out.println("3-Exit");
            out.print("Choose  an option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: // Sign in
                    out.print("Enter username: ");
                    String username = sc.nextLine();
                    out.print("Enter password: ");
                    String password = sc.nextLine();
                    User loggedInUser = userManager.loginUser(username, password);
                    boolean success = loggedInUser != null;
                    if(success) {
                        user = userManager.getUserById(username);
                        if (user.getRole().equals("USER")) {
                            out.println("1. My Active Events");
                            out.println("2. Active Events");
                            out.println("3. New Event");
                            out.println("4. Events Description");
                            out.println("5. Search by Budget");
                            out.println("6. Track My Expenses");
                            out.println("7. Add Media to My Event");
                            out.println("8. View My Event Media");
                            out.println("9. Remove Media from My Event");
                            out.println("10. Calender");
                            out.println("11. Cancellation");
                            out.println("12. Search for Service Providers");
                            out.println("Choose an option: ");
                            int userChoice = sc.nextInt();
                            switch (userChoice) {
                                case 1: // User's Active Events Menu
                                    if (user.getHallnumber() == null)
                                        out.println("You don't have an active event");
                                    else {
                                        out.print("You have an active Event in: " + user.getHallnumber() + "    Enter 1 to Manage Or 2 to exit: ");
                                        int manageChoice = sc.nextInt();
                                        if (manageChoice == 1) {
                                            out.println("1. Delete Event");
                                            int deleteChoice = sc.nextInt();
                                            if(deleteChoice == 1){
                                                out.println("You'll Be Charged 70% Of the Amount, Are you Sure You Want To Cancel The Reservation? Y/N: ");
                                                sc.nextLine();
                                                String CancelRes = sc.nextLine();
                                                if(CancelRes.equalsIgnoreCase("Y")){
                                                    user.setHallnumber(null);
                                                    boolean updated = exManager.updateFirstExpenseAmountInCategory(username, " Hall  Reservation","Canceled Hall Reservation", 0.7*exManager.getAmountByCategoryForUser(username,"Hall  Reservation"));
                                                    if (updated) {
                                                        logger.info("Your Hall Reservation Expense has been Modified");
                                                    } else {
                                                        logger.info("No Hall Reservation Expense Was Found To Update.");
                                                    }
                                                    out.println("Event Deleted Successfully");
                                                }
                                            }
                                            else break;
                                        }
                                        else break;
                                    }
                                    break;
                                case 2: // All Active Events
                                    userManager.printActiveEvents();
                                    break;
                                case 3: // Reserve New Event
                                    out.println("1. Hall1:\nDate: 15/5/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Nablus\nPeople: 300\nTheme: Dark Grey\nDescription: " +
                                            "Contains fans, each table  takes up to 5 people, Price: 2500 ils\n");
                                    out.println("2. Hall2:\nDate: 25/5/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Tulkarm\nPeople: 400\nTheme: Off white\nDescription: " +
                                            "Contains air conditioning,  each table takes up to 10 people, Price: 3500 ils\n");
                                    out.println("3. Hall3:\nDate: 15/6/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Jenin\nPeople: 500\nTheme: Sky Blue\nDescription: " +
                                            "Contains air conditioning, each table  takes up to 15 people, Price: 4500 ils\n");
                                    out.println("4. Hall4:\nDate: 25/6/2024\nTime: 6:00 PM - 10:00 PM\nLocation: Kalkelye\nPeople: 600\nTheme: Dark Blue\nDescription: " +
                                            "Contains air conditioning, each  table takes up to 20 people, Price: 5500 ils\n");
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
                                            hallNumber = null;
                                            break;
                                    }
                                    if(hallNumber != null){
                                        user.setHallnumber(hallNumber);
                                        exManager.addExpense(username,"Hall  Reservation",500,"Reservation Of "+hallNumber+" Without Chief or DJ");
                                    }

                                    break;

                                case 4: // Events Description
                                    out.println("1. Hall1");
                                    out.println("2. Hall2");
                                    out.println("3. Hall3");
                                    out.println("4. Hall4");
                                    out.println("Choose Which Hall You Want The Description Of: ");
                                    int desChoice = sc.nextInt();
                                    switch (desChoice){
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
                                        default:
                                            out.println("INVALID!!");
                                    }
                                    break;
                                case 5: //Search By Budget
                                    out.println("Enter your budget: ");
                                    int budget = sc.nextInt();
                                    if(budget<500){
                                        out.println("There's No Available Packages For This Budget");
                                        break;
                                    }

                                    list.searchBelowCost(budget);
                                    out.println("select package or exit");
                                    out.println("*Note that you'll be charged 70% of the Reservation Fee If You Canceled Under Any Situation*");

                                    sc.nextLine();
                                    String packname = sc.nextLine();
                                    int hallchoice;
                                    String sentence="select hall number form 1 to 4";
                                    String invalidSentence="invalid input";
                                    switch (packname.toUpperCase()){
                                        case "HALL ONLY":
                                            if(budget>=500){
                                                out.println(sentence);
                                                hallchoice = sc.nextInt();
                                                if(hallchoice>=1&&hallchoice<=4){
                                                    user.setHallnumber("Hall"+hallchoice);
                                                    exManager.addExpense(username,"Hall Reservation ",500,"Reservation  Of Hall Number "+hallchoice+" Without Chief Or DJ" );
                                                }
                                                else out.println(invalidSentence);
                                            }
                                            break;
                                        case "HALL WITH CHIEF":
                                            if(budget>=650){
                                                out.println(sentence);
                                                hallchoice = sc.nextInt();
                                                if(hallchoice>=1&&hallchoice<=4){
                                                    user.setHallnumber("Hall"+hallchoice+" With Chief");
                                                    exManager.addExpense(username,"hall Reservation",650," Reservation Of Hall Number "+hallchoice+" With Chief" );

                                                }
                                                else out.println(invalidSentence);
                                            }

                                            break;
                                        case "HALL WITH DJ":
                                            if(budget>=570){
                                                out.println(sentence);
                                                hallchoice = sc.nextInt();
                                                if(hallchoice>=1&&hallchoice<=4){
                                                    user.setHallnumber("Hall"+hallchoice+" With DJ");
                                                    exManager.addExpense(username,"Hall Reservation",570,"Reservation Of Hall Number "+hallchoice+" With DJ" );

                                                }
                                                else out.println(invalidSentence);
                                            }

                                            break;
                                        case "HALL WITH DJ AND CHIEF":
                                            if(budget>=700){
                                                out.println(sentence);
                                                hallchoice = sc.nextInt();
                                                if(hallchoice>=1&&hallchoice<=4){
                                                    user.setHallnumber("Hall"+hallchoice+" With DJ and Chief");
                                                    exManager.addExpense(username,"Hall Reservation",700,"Reservation Of Hall Number "+hallchoice+" With Chief And DJ" );


                                                }
                                                else out.println(invalidSentence);
                                            }

                                            break;
                                        case "EXIT":

                                            break;

                                        default:
                                            out.println("Invalid Input: "+packname);
                                    }

                                    break;

                                case 6:
                                    exManager.printExpensesForUser(username);

                                    break;
                                case 7: // Add Media to Event
                                    sc.nextLine();
                                    out.println("Enter the type of media (e.g., 'image', 'video'): ");
                                    String type = sc.nextLine();
                                    out.println("Enter the URL or path to the media: ");
                                    String url = sc.nextLine();
                                    Media mediaToAdd = new Media(type, url);
                                    userManager.addMediaToUserEvent(username, mediaToAdd);
                                    break;

                                case 8: // View My Event Media
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

                                case 9: // Remove Media from Event
                                    out.println("Enter the URL of the media you wish to remove: ");
                                    String mediaUrl = sc.nextLine();
                                    Media mediaToRemove = new Media("", mediaUrl);
                                    userManager.removeMediaFromUserEvent(username, mediaToRemove);
                                    break;
                                case 10:

                                    try {
                                        out.println("Displaying available venues and important dates...");
                                        venueservice.DisplayCalender();
                                        venueservice.displayVenues();

                                        out.println("Please enter the venue ID you wish to book:");
                                        sc.nextLine();
                                        String venueId = sc.nextLine();
                                        venueBookingSteps.findASuitableVenue(venueId);

                                        out.println("Please enter the date you wish to book the venue for (YYYY-MM-DD):");
                                        date = sc.nextLine();
                                        venueBookingSteps.reserveVenueForSpecificDate(date);
                                        out.println("Venue booked successfully!");
                                        venueBookingSteps.confirmTheReservation();
                                    } catch (IllegalStateException e) {
                                        out.println("Booking failed: " + e.getMessage());
                                    }
                                    break;


                                case 11:
                                    try {
                                        logger.info("Enter the venue ID for the reservation to cancel:");
                                        sc.nextLine();
                                        String venueId = sc.nextLine();
                                        venueBookingSteps.cancelReservation(venueId);
                                    } catch (IllegalStateException e) {
                                        logger.info("Cancellation failed: " + e.getMessage());
                                        break;

                                    }
                                    break;
                                case 12:
                                    sc.nextLine(); // Consume any leftover newline character
                                    logger.info("Enter  service type: ");
                                    String servicetype = sc.nextLine();
                                    logger.info("Enter  location: ");
                                    String location = sc.nextLine();
                                    logger.info("Enter maximum pricing: ");
                                    double maxPricing = sc.nextDouble();
                                    logger.info("Enter minimum rating: ");
                                    double minRating = sc.nextDouble();
                                    sc.nextLine(); // Consume newline left after nextDouble()

                                    List<ServiceProvider> results = userManager.searchServiceProviders(servicetype, location, maxPricing, minRating);
                                    if (results.isEmpty()) {
                                        logger.info("No service providers found matching the criteria.");
                                    } else {
                                        logger.info("Found service providers:");
                                        for (ServiceProvider provider : results) {
                                            logger.info("Name: " + provider.getUsername() + ", Location: " + provider.getLocation() +
                                                    ", Pricing: " + provider.getPricing() + ", Rating: " + provider.getRating());
                                        }
                                    }
                                    break;
                                default:
                                    out.println("Invalid option.  Please try again.");
                            }

                        }
                        if (user.getRole().equals("ADMIN")) {
                            out.println("1. Active Events");
                            out.println("2. Register New User");
                            out.println("3. Events Description");
                            out.println("4. Users Expenses");
                            out.println("5. Search User Expenses");
                            out.println("6. Search for Service Providers");
                            out.println("Choose an option: ");
                            int userChoice = sc.nextInt();
                            switch (userChoice){
                                case 1: // All Active Events
                                    userManager.printActiveEvents();
                                    break;

                                case 2: // Register New User
                                    out.println("enter Username: ");
                                    String newUsername = sc.nextLine();
                                    out.println("enter Password: ");
                                    String newPassword = sc.nextLine();
                                    out.println("enter Role: ");
                                    String newRole = sc.nextLine();
                                    out.println("enter Hall Number: ");
                                    String newHallNumber = sc.nextLine();
                                    userManager.registerUser(newUsername, newPassword, newRole, newHallNumber); // Register the new user
                                    out.println("User registered successfully!\n");
                                    break;

                                case 3: // Events Description
                                    out.println("1. Hall1");
                                    out.println("2. Hall2");
                                    out.println("3. Hall3");
                                    out.println("4. Hall4");
                                    out.println("Choose Which Hall You Want The Description Of: ");
                                    int desChoice = sc.nextInt();
                                    switch (desChoice){
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
                                        default:
                                            out.println("INVALID!!");
                                    }
                                    break;

                                case 4: // Users Expenses
                                    exManager.printAllUsersExpenses();

                                    break;

                                case 5:// Search User Expenses
                                    out.print("Enter The Name Of The User: ");
                                    sc.nextLine();
                                    String userNameExp = sc.nextLine();
                                    exManager.printExpensesForUser(userNameExp);
                                    break;

                                case 6:
                                    sc.nextLine(); // Consume any leftover newline character
                                    logger.info("Enter service type: ");
                                    String servicetype = sc.nextLine();
                                    logger.info("Enter location: ");
                                    String location = sc.nextLine();
                                    logger.info("Enter maximum pricing: ");
                                    double maxPricing = sc.nextDouble();
                                    logger.info("Enter minimum rating: ");
                                    double minRating = sc.nextDouble();
                                    sc.nextLine(); // Consume newline left after nextDouble()

                                    List<ServiceProvider> results = userManager.searchServiceProviders(servicetype, location, maxPricing, minRating);
                                    if (results.isEmpty()) {
                                        logger.info("No service providers found matching the criteria.");
                                    } else {
                                        logger.info("Found service providers:");
                                        for (ServiceProvider provider : results) {
                                            logger.info("Name: " + provider.getUsername() + ", Location: " + provider.getLocation() +
                                                    ", Pricing: " + provider.getPricing() + ", Rating: " + provider.getRating());
                                        }
                                    }
                                    break;

                                default:
                                    out.println("Invalid option.  Please try again.");
                            }
                        }
                    }
                    if (!success) {
                        out.println("Login failed!");
                    }
                    break;
                case 2: // Sign up
                    out.print("Enter username: ");
                    String newUsername = sc.nextLine().trim(); // Use trim() to remove leading and trailing spaces
                    out.print("Enter password: ");
                    String newPassword = sc.nextLine().trim(); // Use trim() to remove leading and trailing spaces
                    out.print("Enter role (ADMIN, SERVICE_PROVIDER, USER): ");
                    String role = sc.nextLine().trim().toUpperCase(); // Normalize role input to uppercase and trim spaces

                    if (role.equals("SERVICE_PROVIDER")) {
                        out.print("Enter hall number (or 'none' if not applicable): ");
                        String hallNumberInput = sc.nextLine().trim();
                        // Normalize 'none' input to null to match method expectation for optional hallNumber
                        String hallNumber = hallNumberInput.equalsIgnoreCase("none") ? null : hallNumberInput;

                        out.print("Enter service type: ");
                        String serviceType = sc.nextLine().trim();
                        out.print("Enter location: ");
                        String location = sc.nextLine().trim();
                        out.print("Enter pricing: ");
                        double pricing = sc.nextDouble();
                        out.print("Enter rating: ");
                        double rating = sc.nextDouble();
                        sc.nextLine(); // Consume newline left after nextDouble()

                        userManager.registerUser(newUsername, newPassword, hallNumber, serviceType, location, pricing, rating);
                        logger.info("Service provider registered successfully!\n");
                    } else {
                        // This call now properly handles both ADMIN and USER roles without assuming a hall number for admins
                        userManager.registerUser(newUsername, newPassword, role, (role.equals("ADMIN") ? null : "some default hall number or prompt for it"));
                        logger.info(role + " user registered successfully!\n");
                    }
                    break;


                case 3: // Exit
                    exit = true;
                    break;
                default:
                    out.println("Invalid option.Please try again.");
                    break;
            }
        }
        sc.close();
    }

}