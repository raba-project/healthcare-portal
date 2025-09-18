package com.healthcare.ui;

import com.healthcare.model.Doctor;
import com.healthcare.service.AdminService;
import com.healthcare.ui.ConsoleUtils;

import java.util.List;

import com.healthcare.model.*;

public class AdminMenu {
    private AdminService adminService = new AdminService();

    public void show(User user) throws Exception {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Department");
            System.out.println("2. Add Doctor");
            System.out.println("0. Logout");

            int choice = ConsoleUtils.readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    String dept = ConsoleUtils.readLine("Enter Department Name: ");
                    List<Department> depts = adminService.addDepartment(dept);
                    
                    System.out.println("Departments:");
                    for(Department d : depts) {
                    	System.out.println(d.getName());
                    }
                    System.out.println("Department added.");
                    break;
                case 2:
                	int docUsertId = ConsoleUtils.readInt("Enter User ID: ");
                    int deptId = ConsoleUtils.readInt("Enter Department ID: ");
                    String specialization = ConsoleUtils.readLine("Enter Specialization: ");
                    String availability = ConsoleUtils.readLine("Enter Availability: ");
                    Doctor d = new Doctor(0, docUsertId, deptId, specialization, availability);
                    adminService.addDoctor(d);
                    System.out.println("Doctor added.");
                    break;
                case 0:
                    return;
            }
        }
    }
}
