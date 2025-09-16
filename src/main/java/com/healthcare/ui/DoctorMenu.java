package com.healthcare.ui;

import com.healthcare.dao.DoctorDAO;
import com.healthcare.ui.ConsoleUtils;
import com.healthcare.model.*;

public class DoctorMenu {
    private DoctorDAO doctorDAO = new DoctorDAO();

    public void show(User user) throws Exception {
    	Doctor doctor = doctorDAO.findByUserId(user.getUserId());
        while (true) {
            System.out.println("\n--- Doctor Menu ---");
            System.out.println("1. Update Availability");
            System.out.println("0. Logout");

            int choice = ConsoleUtils.readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    String avail = ConsoleUtils.readLine("Enter Availability: ");
                    doctorDAO.updateAvailability(doctor.getDoctorId(), avail);
                    System.out.println("Availability updated.");
                    break;
                case 0:
                    return;
            }
        }
    }
}
