package com.healthcare.dao;

import com.healthcare.model.Prescription;
import com.healthcare.dao.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {

    public void addPrescription(Prescription prescription) throws SQLException {
        String sql = "INSERT INTO prescriptions (appointment_id, doctor_notes, medicines) VALUES (?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, prescription.getAppointmentId());
            ps.setString(2, prescription.getDoctorNotes());
            ps.setString(3, prescription.getMedicines());
            ps.executeUpdate();
        }
    }

    public List<Prescription> getPrescriptions() throws SQLException {
        List<Prescription> list = new ArrayList<>();
        String sql = "SELECT * FROM prescriptions";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Prescription p = new Prescription(
                        rs.getInt("prescription_id"),
                        rs.getInt("appointment_id"),
                        rs.getString("doctor_notes"),
                        rs.getString("medicines")
                );
                list.add(p);
            }
        }
        return list;
    }
}
