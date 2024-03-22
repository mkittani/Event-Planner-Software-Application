package wedding.Planner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {
    private Map<String, List<Expense>> userExpenses = new HashMap<>();

    public void addExpense(String username, String category, double amount, String description) {
        Expense expense = new Expense(category, amount, description);
        userExpenses.putIfAbsent(username, new ArrayList<>());
        userExpenses.get(username).add(expense);
    }
    public boolean updateFirstExpenseAmountInCategory(String username, String category,String newCategory, double newAmount) {
        List<Expense> expenses = userExpenses.get(username);
        if (expenses != null) {
            for (Expense expense : expenses) {
                if (expense.getCategory().equals(category)) {
                    expense.setAmount(newAmount);
                    expense.setCategory(newCategory);
                    return true; // Update was successful
                }
            }
        }
        return false; // No matching expense found to update
    }

    public double getAmountByCategoryForUser(String username, String category) {
        double totalAmount = 0;
        List<Expense> expenses = userExpenses.get(username);
        if (expenses != null) {
            for (Expense expense : expenses) {
                if (expense.getCategory().equals(category)) {
                    totalAmount = expense.getAmount();
                }
            }
        }
        return totalAmount;
    }




    public void printExpensesForUser(String username) {
        List<Expense> expenses = userExpenses.getOrDefault(username, new ArrayList<>());
        if (expenses.isEmpty()) {
            System.out.println("No expenses found for user: " + username);
        } else {
            System.out.println("Expenses for " + username + ":");
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        }
    }
    public void printAllUsersExpenses() {
        if (userExpenses.isEmpty()) {
            System.out.println("No expenses found for any user.");
            return;
        }

        for (Map.Entry<String, List<Expense>> entry : userExpenses.entrySet()) {
            String username = entry.getKey();
            List<Expense> expenses = entry.getValue();

            System.out.println("Expenses for " + username + ":");
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
            System.out.println(); // Add a blank line for better readability between users
        }
    }
}
