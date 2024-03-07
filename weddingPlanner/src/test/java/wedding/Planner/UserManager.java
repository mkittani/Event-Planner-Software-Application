package wedding.Planner;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.*;

public class UserManager {
    private static User info;
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
            System.out.println("Login successful for " + user.getRole() + ": " + username);
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
            out.println("1-Sign in");
            out.println("2-Sign up");
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
                    if (!success) {
                        out.println("Login failed!");
                    }
                    break;
                case 2: // Sign up
                    out.print("Enter username: ");
                    String newUsername = sc.nextLine();
                    info.setUsername(newUsername);
                    out.print("Enter password: ");
                    String newPassword = sc.nextLine();
                    info.setPassword(newPassword);
                    out.print("Enter role (ADMIN, SERVICE_PROVIDER, USER): ");
                    String role = sc.nextLine();
                    info.setRole(role);
//                    out.print("Enter hall number: ");  // Ask for the hall number
                    String hallNumber = sc.nextLine();  // Read the hall number
                    userManager.registerUser(newUsername, newPassword, role, hallNumber);  // Pass the hall number as an argument
                    out.println("User registered successfully!");
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
