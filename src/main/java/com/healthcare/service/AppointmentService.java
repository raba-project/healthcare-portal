package com.healthcare.service;

import com.healthcare.dao.AppointmentDAO;
import com.healthcare.model.Appointment;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class AppointmentService {
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    public void bookAppointment(int patientId, int doctorId, Timestamp date) throws SQLException {
        Appointment a = new Appointment(0, patientId, doctorId, date, "BOOKED");
        appointmentDAO.addAppointment(a);
    }

    public List<Appointment> getAllAppointments() throws SQLException {
        return appointmentDAO.getAppointments();
    }
}
