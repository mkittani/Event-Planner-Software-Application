package wedding.Planner;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.*;

public class UserManager {
//    private static User info;
//    private static RegularUser info;
    private Map<String, User> users = new HashMap<>();
    public User getUserById(String username) {
        return users.get(username); // This will return the user associated with the username, or null if no such user exists
    }

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
           // out.println("3-Sign up");
            out.println("3-Exit");

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
                    String hallNumber;
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
                            out.println("Invalid hall number. Setting default to 'Hall1'.");
                            hallNumber = "Hall1"; // Set a default value or handle this case as you see fit
                            break;
                    }

                    userManager.registerUser(newUsername, newPassword, role, hallNumber); // Register the new user
                    out.println("User registered successfully!\n");
                    // Display or do additional stuff as needed after successful registration.

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

        // Attempt to login
      //  userManager.loginUser("adminUser", "adminPass"); // Should succeed
       // userManager.loginUser("regularUser", "wrongPass"); // Should fail
    }

}
