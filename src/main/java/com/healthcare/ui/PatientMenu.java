package com.healthcare.ui;

import com.healthcare.service.AppointmentService;
import com.healthcare.service.BillingService;
import com.healthcare.ui.ConsoleUtils;
import com.healthcare.model.*;
import com.healthcare.dao.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class PatientMenu {
    private AppointmentService appointmentService = new AppointmentService();
    private BillingService billingService = new BillingService();
    private PatientDAO patientDAO = new PatientDAO();
    
    public void show(User user) throws Exception {
    	Patient patient = patientDAO.findByUserId(user.getUserId());
        while (true) {
            System.out.println("\n--- Patient Menu ---");
            System.out.println("1. Book Appointment");
            System.out.println("2. Cancel Appointment");
            System.out.println("3. View Doctors");
            System.out.println("0. Logout");

            int choice = ConsoleUtils.readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    int doctorId = ConsoleUtils.readInt("Enter Doctor ID: ");
                    int id = appointmentService.bookAppointment(patient.getPatientId(), doctorId, new Timestamp(new Date().getTime()));
                    System.out.println("Appointment booked! with ID : " + id);
                    break;
                case 2:
                    int apptId = ConsoleUtils.readInt("Enter Appointment ID: ");
                    appointmentService.cancelAppointment(apptId);
                    System.out.println("Appointment cancelled!");
                    break;
                case 3:
                    List<Doctor> doctors = new DoctorDAO().getAllDoctors();
                    System.out.println("Doctors:");
                    System.out.println("DocID" + "  " + "Specialization" + "  " + "Availability");
                    for(Doctor d : doctors) {
                    	System.out.println(d.getDoctorId() + "  " + d.getUserId() + "  " + d.getSpecialization() + "  " + d.getDeptId() + "  " + d.getAvailability());
                    }
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
