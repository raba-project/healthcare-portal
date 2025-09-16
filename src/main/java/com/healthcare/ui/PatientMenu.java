package com.healthcare.ui;

import com.healthcare.service.AppointmentService;
import com.healthcare.service.BillingService;
import com.healthcare.ui.ConsoleUtils;
import com.healthcare.model.*;

import java.sql.Timestamp;
import java.util.Date;

public class PatientMenu {
    private AppointmentService appointmentService = new AppointmentService();
    private BillingService billingService = new BillingService();

    public void show(User user) throws Exception {
        while (true) {
            System.out.println("\n--- Patient Menu ---");
            System.out.println("1. Book Appointment");
            System.out.println("2. View Bills");
            System.out.println("0. Logout");

            int choice = ConsoleUtils.readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    int doctorId = ConsoleUtils.readInt("Enter Doctor ID: ");
                    appointmentService.bookAppointment(user.getUserId(), doctorId, new Timestamp(new Date().getTime()));
                    System.out.println("Appointment booked!");
                    break;
                case 2:
                    int apptId = ConsoleUtils.readInt("Enter Appointment ID: ");
                    System.out.println("Bill: $" + billingService.getBill(apptId));
                    break;
                case 0:
                    return;
            }
        }
    }
}
