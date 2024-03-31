package wedding.Planner;

public class Admin extends User {
    public Admin(String username, String password, String hallnumber) {
        super(username, password, "ADMIN",hallnumber);
    }
}
