package wedding.Planner;
import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

public class UserManager {

    private Map<String, Package> requestedPackages = new HashMap<>();
    private Map<String, String> negotiatedContracts = new HashMap<>(); // Assuming simple representation for demo
    private Map<String, List<Booking>> userBookings = new HashMap<>();
    private Map<String, User> users = new HashMap<>();
    private Map<String, List<ServiceProvider>> serviceProviders = new HashMap<>();



    //-------------------------------------------
    /*
    public void requestPackage(String username, String packageName, double cost) {
        Package pkg = new Package(packageName, cost);
        requestedPackages.put(username, pkg);
    }

    // Assuming a package has been requested by checking if the user has a package associated
    public boolean isPackageRequested(String username, String packageName) {
        Package pkg = requestedPackages.get(username);
        return pkg != null && pkg.getName().equals(packageName);
    }

    // Simulate contract negotiation with a package name, for simplicity
    public void negotiateContractTerms(String username, String packageName, String newTerms) {
        // Here, newTerms could represent adjustments to the package, like adding services
        // This method assumes you're tracking negotiation by package name for simplicity
        negotiatedContracts.put(username, packageName + ": " + newTerms);
    }

    // Check if terms were negotiated for a given package
    public boolean areContractTermsUpdated(String username, String packageName) {
        String contractDetails = negotiatedContracts.get(username);
        return contractDetails != null && contractDetails.startsWith(packageName);
    }

    // Accept a contract and confirm a booking, for simplicity assuming always successful
    public boolean acceptContract(String username, String packageName) {
        Package pkg = requestedPackages.get(username);
        if (pkg != null && pkg.getName().equals(packageName)) {
            addBooking(username, packageName); // A simple method to add a booking based on package name
            return true;
        }
        return false;
    }

    // Method to decline a contract
    public void declineContract(String username) {
        negotiatedContracts.remove(username); // Remove negotiation details
        requestedPackages.remove(username); // Optionally remove the requested package
    }

    // Add a booking for a user based on a package name
    public void addBooking(String username, String packageName, String date) {
        Package pkg = requestedPackages.get(username);
        if (pkg != null) {
            // Assuming you have a method or logic to convert package name to Venue object
            Venue venue = getVenueFromPackageName(packageName);
            Booking booking = new Booking(venue, date);
            userBookings.computeIfAbsent(username, k -> new ArrayList<>()).add(booking);
        }
    }
    private Venue getVenueFromPackageName(String packageName) {
        // Here, you would have some logic to determine the Venue based on the package's name.
        // This could involve looking up a Venue directly associated with a Package, or
        // perhaps packages are named in a way that includes the venue's information.
        // For simplicity, let's assume a default Venue for demonstration:
        return new Venue("Default Venue Name", "Default Location", 100); // Simplified constructor for Venue
    }

    // Retrieve bookings for a user
    public List<Booking> getBookings(String username) {
        return userBookings.getOrDefault(username, Collections.emptyList());
    }
    //------------------------------------------

     */
    private EventMediaManager mediaManager = new EventMediaManager(); // Media manager instance

    private static User user;
    private static PackageList list = new PackageList();

    public User getUserById(String username) {
        return users.get(username); // This will return the user associated with the username, or null if no such user exists
    }
    public void registerUser(String username, String password, String role, String hallnumber) {
        User newUser;
        if ("ADMIN".equalsIgnoreCase(role)) {
            newUser = new Admin(username, password, hallnumber); // Assuming an Admin class exists
        } else { // Default to USER
            newUser = new RegularUser(username, password, hallnumber); // Assuming a RegularUser class exists
        }
        users.put(username, newUser);
        System.out.println(role + " registered successfully.");
    }

    // Specific method for SERVICE_PROVIDER registration
    public void registerUser(String username, String password, String role, String hallNumber, String serviceType, String location, double pricing, double rating) {
        hallNumber = (hallNumber == null || hallNumber.equalsIgnoreCase("none")) ? "" : hallNumber;
        ServiceProvider serviceProvider = new ServiceProvider(username, password, hallNumber, serviceType, location, pricing, rating);
        users.put(username, serviceProvider); // Add to general users
        serviceProviders.computeIfAbsent(serviceType.toLowerCase(), k -> new ArrayList<>()).add(serviceProvider); // Add to service providers
        System.out.println("Service provider registered successfully.");
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
    // 1.9+1.10+1.11
    public void addServiceProvider(ServiceProvider serviceProvider) {
        // Add the service provider to the specific service type list
        serviceProviders.computeIfAbsent(serviceProvider.getServiceType().toLowerCase(), k -> new ArrayList<>()).add(serviceProvider);
        // Debugging line to confirm addition
        System.out.println("Added service provider: " + serviceProvider.getUsername() + " under service type: " + serviceProvider.getServiceType());
    }


    public List<ServiceProvider> searchServiceProviders(String type, String location, double maxPricing, double minRating) {
        // Print search parameters for debugging
        System.out.println("Searching for: Type=" + type + ", Location=" + location + ", Max Pricing=" + maxPricing + ", Min Rating=" + minRating);

        // Perform search with case-insensitive match for type and location
        return serviceProviders.getOrDefault(type.toLowerCase(), Collections.emptyList()).stream()
                .filter(provider -> provider.getLocation().equalsIgnoreCase(location))
                .filter(provider -> provider.getPricing() <= maxPricing)
                .filter(provider -> provider.getRating() >= minRating)
                .collect(Collectors.toList());
    }
    //--------------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        VenueBookingSteps venueBookingSteps = new VenueBookingSteps();
        VenueService  venueservice = new VenueService();
        ExpenseManager ExManager = new ExpenseManager();
        String date;

        list.addPackage(new Package("Hall Only",500));
        list.addPackage(new Package("Hall With Chief",650));
        list.addPackage(new Package("Hall With DJ",570));
        list.addPackage(new Package("Hall With DJ and Chief",700));

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
            out.println("3-Exit");
            // out.println("3-Sign up");

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
                            out.println("5. Search by Budget");
                            out.println("6. Track My Expenses");
                            //-----------------------------------Osama Salah---------------------------------------------------------------------------------------
                            out.println("7. Add Media to My Event");
                            out.println("8. View My Event Media");
                            out.println("9. Remove Media from My Event");
                            out.println("10. Calender");
                            out.println("11. Cancellation");
                            out.println("12. Search for Service Providers");
                            //-----------------------------------Osama Salah---------------------------------------------------------------------------------------
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
                                                    boolean updated = ExManager.updateFirstExpenseAmountInCategory(username, "Hall Reservation","Canceled Hall Reservation", 0.7*ExManager.getAmountByCategoryForUser(username,"Hall Reservation"));
                                                    if (updated) {
                                                        System.out.println("Your Hall Reservation Expense has been Modified");
                                                    } else {
                                                        System.out.println("No Hall Reservation Expense Was Found To Update.");
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


                                    if(hallNumber != null){
                                        user.setHallnumber(hallNumber);
                                        ExManager.addExpense(username,"Hall Reservation",500,"Reservation Of "+hallNumber+" Without Chief or DJ");
                                    }

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
                                    switch (packname.toUpperCase()){
                                        case "HALL ONLY":
                                            if(budget>=500){
                                                out.println("select hall number form 1 to 4");
                                                hallchoice = sc.nextInt();
                                                if(hallchoice>=1&&hallchoice<=4){
                                                    user.setHallnumber("Hall"+hallchoice);
                                                    ExManager.addExpense(username,"Hall Reservation",500,"Reservation Of Hall Number "+hallchoice+" Without Chief Or DJ" );
//                                                    ExManager.printExpensesForUser(username);
                                                }
                                                else out.println("invalid input");
                                            }
                                            break;
                                        case "HALL WITH CHIEF":
                                            if(budget>=650){
                                                out.println("select hall number form 1 to 4");
                                                hallchoice = sc.nextInt();
                                                if(hallchoice>=1&&hallchoice<=4){
                                                    user.setHallnumber("Hall"+hallchoice+" With Chief");
                                                    ExManager.addExpense(username,"Hall Reservation",650,"Reservation Of Hall Number "+hallchoice+" With Chief" );

                                                }
                                                else out.println("invalid input");
                                            }

                                            break;
                                        case "HALL WITH DJ":
                                            if(budget>=570){
                                                out.println("select hall number form 1 to 4");
                                                hallchoice = sc.nextInt();
                                                if(hallchoice>=1&&hallchoice<=4){
                                                    user.setHallnumber("Hall"+hallchoice+" With DJ");
                                                    ExManager.addExpense(username,"Hall Reservation",570,"Reservation Of Hall Number "+hallchoice+" With DJ" );

                                                }
                                                else out.println("invalid input");
                                            }

                                            break;
                                        case "HALL WITH DJ AND CHIEF":
                                            if(budget>=700){
                                                out.println("select hall number form 1 to 4");
                                                hallchoice = sc.nextInt();
                                                if(hallchoice>=1&&hallchoice<=4){
                                                    user.setHallnumber("Hall"+hallchoice+" With DJ and Chief");
                                                    ExManager.addExpense(username,"Hall Reservation",700,"Reservation Of Hall Number "+hallchoice+" With Chief And DJ" );


                                                }
                                                else out.println("invalid input");
                                            }

                                            break;
                                        case "EXIT":

                                            break;

                                        default:
                                            out.println("Invalid Input: "+packname);
                                    }

                                    break;

                                case 6:
                                    ExManager.printExpensesForUser(username);

                                    break;



                                //---------------------------Osama Salah-----------------------------------------------------------------------------------------------
                                case 7: // Add Media to Event
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
                                    // Assuming the media type isn't necessary for removal, adjust as needed
                                    Media mediaToRemove = new Media("", mediaUrl); // Empty type, only URL needed for this example
                                    userManager.removeMediaFromUserEvent(username, mediaToRemove);
                                    break;
                                case 10:

                                    try {
                                        out.println("Displaying available venues and important dates...");
                                        // Display venues and dates here, as needed
                                        venueservice.DisplayCalender();
                                        venueservice.displayVenues();

                                        out.println("Please enter the venue ID you wish to book:");
                                        sc.nextLine();
                                        String venueId = sc.nextLine(); // Make sure this line executes to read the venue ID
                                        venueBookingSteps.findASuitableVenue(venueId);

                                        out.println("Please enter the date you wish to book the venue for (YYYY-MM-DD):");
                                        date = sc.nextLine(); // Make sure this line executes to read the date
                                        venueBookingSteps.reserveVenueForSpecificDate(date); // Attempt to reserve the venue for the specified date
                                        out.println("Venue booked successfully!");
                                        venueBookingSteps.confirmTheReservation(); // This method should provide additional confirmation
                                    } catch (IllegalStateException e) {
                                        out.println("Booking failed: " + e.getMessage()); // Print out the error message if booking fails
                                    }
                                    break;


                                case 11:
                                    try {
                                        System.out.println("Enter the venue ID for the reservation to cancel:");
                                        sc.nextLine();
                                        String venueId = sc.nextLine();
                                        venueBookingSteps.cancelReservation(venueId);
                                    } catch (IllegalStateException e) {
                                        System.out.println("Cancellation failed: " + e.getMessage());
                                        break;

                                    }
                                    break;
                                case 12:
                                    sc.nextLine(); // Consume any leftover newline character
                                    System.out.print("Enter service type: ");
                                    String servicetype = sc.nextLine();
                                    System.out.print("Enter location: ");
                                    String location = sc.nextLine();
                                    System.out.print("Enter maximum pricing: ");
                                    double maxPricing = sc.nextDouble();
                                    System.out.print("Enter minimum rating: ");
                                    double minRating = sc.nextDouble();
                                    sc.nextLine(); // Consume newline left after nextDouble()

                                    List<ServiceProvider> results = userManager.searchServiceProviders(servicetype, location, maxPricing, minRating);
                                    if (results.isEmpty()) {
                                        System.out.println("No service providers found matching the criteria.");
                                    } else {
                                        System.out.println("Found service providers:");
                                        for (ServiceProvider provider : results) {
                                            System.out.println("Name: " + provider.getUsername() + ", Location: " + provider.getLocation() +
                                                    ", Pricing: " + provider.getPricing() + ", Rating: " + provider.getRating());
                                        }
                                    }
                                    break;
//--------------------------------------------------------------------------------------------------------------------------
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

                                case 4: // Users Expenses
                                    ExManager.printAllUsersExpenses();

                                    break;

                                case 5:// Search User Expenses
                                    out.print("Enter The Name Of The User: ");
                                    sc.nextLine();
                                    String UserNameExp = sc.nextLine();
                                    ExManager.printExpensesForUser(UserNameExp);
                                    break;

                                default:
                                    throw new IllegalStateException("Unexpected value: " + userChoice);
                            }



                        }
                    }

//--------------------------------------------------------------------------------------------------------------------------

                    if (!success) {
                        out.println("Login failed!");
                    }
                    break;
                case 2: // Sign up
                    System.out.print("Enter username: ");
                    String newUsername = sc.nextLine();
                    System.out.print("Enter password: ");
                    String newPassword = sc.nextLine();
                    System.out.print("Enter role (ADMIN, SERVICE_PROVIDER, USER): ");
                    String role = sc.nextLine();

                    if (role.equalsIgnoreCase("SERVICE_PROVIDER")) {
                        System.out.print("Enter hall number (or 'none' if not applicable): ");
                        String hallNumberInput = sc.nextLine();
                        // Convert 'none' input to null to match our method's expectation for optional hallNumber
                        String hallNumber = hallNumberInput.equalsIgnoreCase("none") ? null : hallNumberInput;

                        System.out.print("Enter service type: ");
                        String serviceType = sc.nextLine();
                        System.out.print("Enter location: ");
                        String location = sc.nextLine();
                        System.out.print("Enter pricing: ");
                        double pricing = sc.nextDouble();
                        System.out.print("Enter rating: ");
                        double rating = sc.nextDouble();
                        sc.nextLine(); // Consume newline left after nextDouble()

                        // Assuming userManager.registerUser() for SERVICE_PROVIDER includes all necessary details
                        userManager.registerUser(newUsername, newPassword, role, hallNumber, serviceType, location, pricing, rating);
                        System.out.println("Service provider registered successfully!\n");
                    } else {
                        // Assuming userManager.registerUser() for ADMIN and USER only requires basic details
                        userManager.registerUser(newUsername, newPassword, role, null); // Pass null for hallNumber for ADMIN and USER
                        System.out.println("User registered successfully!\n");
                    }
                    break;


                case 3: // Exit
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
// 592 SERVICE_PROVIDER menue