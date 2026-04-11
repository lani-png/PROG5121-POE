package com.mycompany.prog5121poe;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the login class.
 * Tests cover username validation, password complexity,
 * cell phone number formatting, and login authentication.
 *
 * @author Lindelani
 */
public class PROG5121poeTest {

    // ===================== USERNAME TESTS =================

    /**
     * Test that a correctly formatted username (contains '_', <= 5 chars) returns true.
     * Test Data: "kyl_1"
     */
    @Test
    public void testUsernameCorrectlyFormatted() {
        login userLogin = new login();
        assertTrue(userLogin.checkUserName("kyl_1"));
    }

    /**
     * Test that an incorrectly formatted username returns false.
     * Test Data: "kyle!!!!!!!"
     */
    @Test
    public void testUsernameIncorrectlyFormatted() {
        login userLogin = new login();
        assertFalse(userLogin.checkUserName("kyle!!!!!!!"));
    }

    // ===================== PASSWORD TESTS =====================

    /**
     * Test that a password meeting all complexity requirements returns true.
     * Test Data: "Ch&&sec@ke99!"
     */
    @Test
    public void testPasswordMeetsComplexityRequirements() {
        login userLogin = new login();
        assertTrue(userLogin.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    /**
     * Test that a password that does NOT meet complexity requirements returns false.
     * Test Data: "password"
     */
    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        login userLogin = new login();
        assertFalse(userLogin.checkPasswordComplexity("password"));
    }

    // ===================== CELL PHONE TESTS =====================

    /**
     * Test that a correctly formatted SA cell number returns true.
     * Test Data: +27838968976
     */
    @Test
    public void testCellPhoneCorrectlyFormatted() {
        login userLogin = new login();
        assertTrue(userLogin.checkCellPhoneNumber("+27838968976"));
    }

    /**
     * Test that an incorrectly formatted cell number returns false.
     * Test Data: 08966553
     */
    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        login userLogin = new login();
        assertFalse(userLogin.checkCellPhoneNumber("08966553"));
    }

    // ===================== LOGIN TESTS =====================

    /**
     * Test that a successful login returns true.
     * Registers a user first, then logs in with correct credentials.
     */
    @Test
    public void testLoginSuccessful() {
        login userLogin = new login();
        userLogin.registerUser("kyle", "something", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(userLogin.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    /**
     * Test that a failed login (wrong password) returns false.
     */
    @Test
    public void testLoginFailed() {
        login userLogin = new login();
        userLogin.registerUser("kyle", "something", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(userLogin.loginUser("j_123", "wrongpassword"));
    }

    // ===================== RETURN LOGIN STATUS TESTS =====================

    /**
     * Test that returnLoginStatus returns the correct welcome message on success.
     */
    @Test
    public void testReturnLoginStatusSuccess() {
        login userLogin = new login();
        userLogin.registerUser("kyle", "something", "kyl_1", "Ch&&sec@ke99!", "+27831234567");
        String result = userLogin.returnLoginStatus(true);
        assertEquals("Welcome kyle something, it is great to see you again.", result);
    }

    /**
     * Test that returnLoginStatus returns the correct error message on failure.
     */
    @Test
    public void testReturnLoginStatusFailure() {
        login userLogin = new login();
        String result = userLogin.returnLoginStatus(false);
        assertEquals("Username or password incorrect, please try again.", result);
    }
}