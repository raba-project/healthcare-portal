package com.healthcare.ui;

import com.healthcare.service.AppointmentService;
import com.healthcare.service.BillingService;
import com.healthcare.ui.ConsoleUtils;
import com.healthcare.model.*;
import com.healthcare.dao.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class PatientMenu {
    private AppointmentService appointmentService = new AppointmentService();
    private BillingService billingService = new BillingService();
    private PatientDAO patientDAO = new PatientDAO();
    
    public void show(User user) throws Exception {
    	Patient patient = patientDAO.findByUserId(user.getUserId());
        while (true) {
            System.out.println("\n--- Patient Menu ---");
            System.out.println("1. Book Appointment");
            System.out.println("2. View Bills");
            System.out.println("0. Logout");

            int choice = ConsoleUtils.readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    int doctorId = ConsoleUtils.readInt("Enter Doctor ID: ");
                    appointmentService.bookAppointment(patient.getPatientId(), doctorId, new Timestamp(new Date().getTime()));
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
    
    public void addPatient(User user) throws Exception {
    	int userId = user.getUserId();
    	System.out.println("--- Patient Register ---");
    	int age = ConsoleUtils.readInt("Age: ");
    	String gender = ConsoleUtils.readLine("Gender: ");
    	String blood_group = ConsoleUtils.readLine("Blood_group: ");
    	
    	Patient patient = new Patient();
    	patient.setUserId(userId);
    	patient.setAge(age);
    	patient.setGender(gender);
    	patient.setBloodGroup(blood_group);
    	
    	patientDAO.addPatient(patient);
    }
}
