package wedding.Planner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Expense {
    private String category;
    private double amount;
    private String date;
    private String description;

    public static String getCurrentDateAsString() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return currentDate.format(formatter);
    }
    public Expense(String category, double amount, String description) {
        this.category = category;
        this.amount = amount;
        this.date = getCurrentDateAsString();
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setCategory(String Category) {
        this.category = Category;
    }

    // Ensure there are getters for all fields for accessing them
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }
    public String getDescription() { return description; }


    @Override
    public String toString() {
        return "Expense{Category='" + category + '\'' +
                ", Amount=" + amount +
                ", Date of Reservation='" + date + '\'' +
                ", Description='" + description + '\'' +
                '}';
    }
}

