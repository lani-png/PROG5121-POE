package com.mycompany.prog5121poe;

/**
 * Login class handles user registration and authentication.
 * This class contains methods for validating username, password,
 * cell phone number, and managing login state.
 *
 * @author Lindelani
 */
public class login {

    // Stored user details after successful registration
    private String registeredUsername;
    private String registeredPassword;
    private String registeredFirstName;
    private String registeredLastName;

    /**
     * Checks that the username contains an underscore
     * and is no more than five characters long.
     *
     * @param userName the username to validate
     * @return true if valid, false otherwise
     */
    public boolean checkUserName(String userName) {
        if (userName.contains("_") && userName.length() <= 5) {
            return true;
        }
        return false;
    }

    /**
     * Checks that the password meets complexity requirements:
     * at least 8 characters, one capital letter, one number,
     * and one special character.
     *
     * Reference for regex approach considered:
     * https://www.baeldung.com/java-regex-password-validation
     *
     * @param password the password to validate
     * @return true if valid, false otherwise
     */
    public boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        return hasUpper && hasDigit && hasSpecial;
    }

    /**
     * Checks that the cell phone number contains the South African
     * international country code (+27) followed by 9 digits (10 digits total).
     *
     * Regex reference: https://www.regular-expressions.info/phone.html
     * South African number format: +27 followed by 9 digits.
     *
     * @param phoneNumber the phone number to validate
     * @return true if correctly formatted, false otherwise
     */
    public boolean checkCellPhoneNumber(String phoneNumber) {
        // +27 followed by exactly 9 digits = 12 characters total
        String regex = "^\\+27[0-9]{9}$";
        return phoneNumber.matches(regex);
    }

    /**
     * Registers a user by storing their credentials if all
     * validation checks pass.
     *
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param userName  the desired username
     * @param password  the desired password
     * @param phoneNum  the user's cell phone number
     * @return a message indicating success or the specific failure reason
     */
    public String registerUser(String firstName, String lastName,
                               String userName, String password, String phoneNum) {

        if (!checkUserName(userName)) {
            return "Username is not correctly formatted, please ensure that your username "
                    + "contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password "
                    + "contains at least eight characters, a capital letter, a number and a "
                    + "special character.";
        }

        if (!checkCellPhoneNumber(phoneNum)) {
            return "Cell phone number incorrectly formatted or does not contain an international "
                    + "code; please correct the number and try again.";
        }

        // All checks passed — store the details
        this.registeredFirstName = firstName;
        this.registeredLastName = lastName;
        this.registeredUsername = userName;
        this.registeredPassword = password;

        return "Registration successful.";
    }

    /**
     * Attempts to log in with the provided credentials.
     *
     * @param userName the username entered by the user
     * @param password the password entered by the user
     * @return true if credentials match, false otherwise
     */
    public boolean loginUser(String userName, String password) {
        if (registeredUsername == null || registeredPassword == null) {
            return false;
        }
        return registeredUsername.equals(userName) && registeredPassword.equals(password);
    }

    /**
     * Returns an appropriate message based on whether login was successful.
     *
     * @param isLoggedIn the result of the login attempt
     * @return welcome message if successful, error message if not
     */
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + registeredFirstName + " " + registeredLastName
                    + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // --- Getters (used by tests and main class) ---

    public String getRegisteredUsername() {
        return registeredUsername;
    }

    public String getRegisteredPassword() {
        return registeredPassword;
    }

    public String getRegisteredFirstName() {
        return registeredFirstName;
    }

    public String getRegisteredLastName() {
        return registeredLastName;
    }
}