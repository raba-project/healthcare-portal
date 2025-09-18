package com.healthcare.ui;

import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class doctormodule_test {

    public static boolean isValidMobile(String mobile) {
        return mobile != null && mobile.matches("^[6-9]\\d{9}$");
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
    public static boolean isValidPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z]).{8,}$");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Mobile: ");
        String mobile = sc.nextLine();
        if (isValidMobile(mobile)) {
            System.out.println(" Mobile Number is valid");
        } else {
            System.out.println(" Mobile Number is in valid");
        }

        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        if (isValidEmail(email)) {
            System.out.println("Email is valid");
        } else {
            System.out.println("email is not valid");
        }
    }
    @Test
    void testValidMobile() {
        assertTrue(isValidMobile("9876543210"), "Valid mobile should pass");
    }

    @Test
    void testInvalidMobile_Length() {
        assertFalse(isValidMobile("12"), "mobile number length should be 10");
    }

    @Test
    void testInvalidMobile_NonDigits() {
        assertFalse(isValidMobile("98ABC43210"), "mobile number cant have any characters");
    }

    @Test
    void testValidEmail() {
        assertTrue(isValidEmail("doctor@example.com"), "Valid email should pass");
    }

    @Test
    void testInvalidEmail_NoAt() {
        assertFalse(isValidEmail("doctorexample.com"), "Missing @ it  should fail");
    }

    @Test
    void testInvalidEmail_BadFormat() {
        assertFalse(isValidEmail("doctor@.com"), "Bad format of email it should fail");
    }

    @Test
    void testValidPassword() {
        assertTrue(isValidPassword("StrongPass1"), "Password with upper, lower, >=8 chars should pass");
    }

    @Test
    void testInvalidPassword_Short() {
        assertFalse(isValidPassword("Abc1"), "Too short should fail");
    }

    @Test
    void testInvalidPassword_NoUppercase() {
        assertFalse(isValidPassword("weakpassword"), "Missing uppercase should fail");
    }
}
