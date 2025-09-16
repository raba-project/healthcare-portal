package com.healthcare.service;

import com.healthcare.dao.DepartmentDAO;
import com.healthcare.dao.DoctorDAO;
import com.healthcare.model.Department;
import com.healthcare.model.Doctor;

import java.sql.SQLException;
import java.util.List;

public class AdminService {
    private DepartmentDAO deptDAO = new DepartmentDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();

    public void addDepartment(String name) throws SQLException {
        deptDAO.addDepartment(new Department(0, name));
    }

    public List<Department> getDepartments() throws SQLException {
        return deptDAO.getAllDepartments();
    }

    public void addDoctor(Doctor doctor) throws SQLException {
        doctorDAO.addDoctor(doctor);
    }

    public List<Doctor> getDoctors() throws SQLException {
        return doctorDAO.getAllDoctors();
    }
}
