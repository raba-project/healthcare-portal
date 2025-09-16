package com.healthcare.dao;

import com.healthcare.model.Patient;
import com.healthcare.model.Role;
import com.healthcare.model.User;
import com.healthcare.dao.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public void addPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patients (user_id, age, gender, blood_group) VALUES (?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, patient.getUserId());
            ps.setInt(2, patient.getAge());
            ps.setString(3, patient.getGender());
            ps.setString(4, patient.getBloodGroup());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                	patient.setPatientId(rs.getInt(1));
                }
            }
        }
    }

    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Patient p = new Patient(
                        rs.getInt("patient_id"),
                        rs.getInt("user_id"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("blood_group")
                );
                list.add(p);
            }
        }
        return list;
    }
    
    public Patient findByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM patients WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Patient p = new Patient();
                    p.setUserId(rs.getInt("user_id"));
                    p.setPatientId(rs.getInt("patient_id"));
                    p.setAge(rs.getInt("age"));
                    p.setGender(rs.getString("gender"));
                    p.setBloodGroup(rs.getString("blood_group"));
                    return p;
                }
            }
        }
        return null;
    }
}
