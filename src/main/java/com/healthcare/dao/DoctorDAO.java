package com.healthcare.dao;

import com.healthcare.model.Doctor;
import com.healthcare.model.Patient;
import com.healthcare.dao.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public void addDoctor(Doctor doctor) throws SQLException {
        String sql = "INSERT INTO doctors (user_id, dept_id, specialization, availability) VALUES (?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, doctor.getUserId());
            ps.setInt(2, doctor.getDeptId());
            ps.setString(3, doctor.getSpecialization());
            ps.setString(4, doctor.getAvailability());
            ps.executeUpdate();
        }
    }

    public List<Doctor> searchBySpecialization(String specialization) throws SQLException {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT * FROM doctors WHERE specialization=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, specialization);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getInt("user_id"),
                        rs.getInt("dept_id"),
                        rs.getString("specialization"),
                        rs.getString("availability")
                ));
            }
        }
        return list;
    }

    public void updateAvailability(int doctorId, String availability) throws SQLException {
        String sql = "UPDATE doctors SET availability=? WHERE doctor_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, availability);
            ps.setInt(2, doctorId);
            ps.executeUpdate();
        }
    }

    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT * FROM doctors";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getInt("user_id"),
                        rs.getInt("dept_id"),
                        rs.getString("specialization"),
                        rs.getString("availability")
                ));
            }
        }
        return list;
    }
    
    public Doctor findByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM doctors WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Doctor d = new Doctor();
                    d.setUserId(rs.getInt("user_id"));
                    d.setDoctorId(rs.getInt("doctor_id"));
                    d.setDeptId(rs.getInt("dept_id"));
                    d.setSpecialization(rs.getString("specialization"));
                    d.setAvailability(rs.getString("availability"));
                    return d;
                }
            }
        }
        return null;
    }
    
    public Doctor findByDoctorId(int doctorId) throws SQLException {
        String sql = "SELECT * FROM doctors WHERE doctor_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Doctor d = new Doctor();
                    d.setUserId(rs.getInt("user_id"));
                    d.setDoctorId(rs.getInt("doctor_id"));
                    d.setDeptId(rs.getInt("dept_id"));
                    d.setSpecialization(rs.getString("specialization"));
                    d.setAvailability(rs.getString("availability"));
                    return d;
                }
            }
        }
        return null;
    }
}
