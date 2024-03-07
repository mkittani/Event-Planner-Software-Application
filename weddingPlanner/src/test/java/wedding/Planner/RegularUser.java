package wedding.Planner;

public class RegularUser extends User {
    public RegularUser(String username, String password, String hallNumber) {
        super(username, password, "USER", hallNumber); // Pass hallNumber to the superclass constructor
    }

}