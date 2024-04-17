package org.example;
import java.util.Scanner;
public class PasswordValidation {
    public static boolean isPasswordValid(String password) {

        // Check if password is null or empty
        if (password == null || password.isEmpty()) {
            return false;
        }

        // Check minimum length
        if (password.length() < 8) {
            return false;
        }

        // Check for lowercase characters
        boolean hasLowerCase = false;
        // Check for uppercase characters
        boolean hasUpperCase = false;
        // Check for digits
        boolean hasDigit = false;
        // Check for symbols
        boolean hasSymbol = false;

        for (char ch : password.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                hasLowerCase = true;
            } else if (Character.isUpperCase(ch)) {
                hasUpperCase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (ch == '_' || ch == '@' || ch == '$') {
                hasSymbol = true;
            }
        }

        // Check if all requirements are good or not
        return hasLowerCase && hasUpperCase && hasDigit && hasSymbol;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input password
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Validate password
        boolean isValid = isPasswordValid(password);

        // Print validation result
        if (isValid) {
            System.out.println("Password is valid.");
        } else {
            System.out.println("Password is invalid.");
        }

        scanner.close();
    }
}
