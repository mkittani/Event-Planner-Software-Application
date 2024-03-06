package wedding.Planner;

public abstract class User {
    protected String username;
    protected String password;
    protected String role;
    protected String hallnumber;




    public User(String username, String password, String role,String hallnumber) {
        this.username = username;
        this.password = password; // Note: In a real application, passwords should be hashed
        this.role = role;
        this.hallnumber=hallnumber;
    }

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

   public String getHallnumber() { return hallnumber; }
   public void setHallnumber(String Hallnumber) { this.hallnumber = hallnumber; }

}