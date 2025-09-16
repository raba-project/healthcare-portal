package com.healthcare.service;

import com.healthcare.dao.AppointmentDAO;
import com.healthcare.dao.DoctorDAO;
import com.healthcare.model.Appointment;
import com.healthcare.model.Doctor;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class AppointmentService {
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    public void bookAppointment(int patientId, int doctorId, Timestamp date) throws SQLException {
    	Doctor doctor = new DoctorDAO().findByUserId(doctorId);
    	if(!doctor.getAvailability().equals("Available")) {
    		throw new SQLException("Doctor is Unavailable! Appointment not Booked", "42000", 999);
    	}
        Appointment a = new Appointment(0, patientId, doctorId, date, "BOOKED");
        appointmentDAO.addAppointment(a);
    }

    public List<Appointment> getAllAppointments() throws SQLException {
        return appointmentDAO.getAppointments();
    }
}
