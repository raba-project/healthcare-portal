package com.healthcare.ui;

import com.healthcare.model.Role;
import com.healthcare.model.User;
import com.healthcare.service.AuthenticationService;
import com.healthcare.ui.*;
import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {
    private Scanner sc = new Scanner(System.in);
    private AuthenticationService authService;

    public MainMenu() {
        try {
            this.authService = new AuthenticationService();
        } catch (Exception e) {
            System.out.println("Error initializing AuthenticationService: " + e.getMessage());
        }
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("=== Healthcare Portal ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine();
            try {
                switch (choice) {
                    case "1":
                        register();
                        break;
                    case "2":
                        login();
                        break;
                    case "3":
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        sc.close();
        System.out.println("Goodbye.");
    }

    private void register() throws Exception {
        System.out.println("--- Register ---");
        System.out.print("Username: "); String u = sc.nextLine();
        System.out.print("Password: "); String p = sc.nextLine();
        System.out.print("Email: "); String e = sc.nextLine();
        System.out.print("Phone: "); String ph = sc.nextLine();
        System.out.print("Role (PATIENT/DOCTOR/ADMIN): "); String role = sc.nextLine().toUpperCase();

        User user = new User();
        user.setUsername(u);
        user.setPassword(p); // NOTE: hash in production
        user.setEmail(e);
        user.setPhone(ph);
        user.setRole(com.healthcare.model.Role.valueOf(role));

        authService.register(user);
        if(user.getRole() == Role.PATIENT) {
            new PatientMenu().addPatient(user);
	    }
        System.out.println("Registered with id: " + user.getUserId());
    }

    private void login() throws Exception {
        System.out.println("--- Login ---");
        System.out.print("Username: "); String u = sc.nextLine();
        System.out.print("Password: "); String p = sc.nextLine();

        User user = authService.login(u, p);
        System.out.println("Welcome, " + user.getUsername() + "! Role: " + user.getRole());

        // Route to the correct menu class
        switch (user.getRole()) {
            case PATIENT:
                new PatientMenu().show(user);
                break;
            case DOCTOR:
                new DoctorMenu().show(user);
                break;
            case ADMIN:
                new AdminMenu().show(user);
                break;
            default:
                System.out.println("Unknown role.");
        }
    }

    public static void main(String[] args) {
        new MainMenu().start();
    }
}
