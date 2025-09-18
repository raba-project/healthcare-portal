package com.healthcare.service;

import com.healthcare.dao.AppointmentDAO;
import com.healthcare.dao.DoctorDAO;
import com.healthcare.dao.PrescriptionDAO;
import com.healthcare.model.Appointment;
import com.healthcare.model.Doctor;
import com.healthcare.model.Prescription;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class AppointmentService {
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    public int bookAppointment(int patientId, int doctorId, Timestamp date) throws SQLException {
    	Doctor doctor = new DoctorDAO().findByDoctorId(doctorId);
    	if(!doctor.getAvailability().equals("Available")) {
    		throw new SQLException("Doctor is Unavailable! Appointment not Booked", "42000", 999);
    	}
        Appointment a = new Appointment(0, patientId, doctorId, date, "BOOKED");
        Appointment returnedAppointment = appointmentDAO.addAppointment(a);
        Prescription p = new Prescription(0, returnedAppointment.getAppointmentId() , "", "");
        new PrescriptionDAO().addPrescription(p);
        return returnedAppointment.getAppointmentId();
    }

    public List<Appointment> getAllAppointments() throws SQLException {
        return appointmentDAO.getAppointments();
    }
    
    public void cancelAppointment(int apptId) throws SQLException {
    	appointmentDAO.cancelAppointment(apptId);
    }
}
