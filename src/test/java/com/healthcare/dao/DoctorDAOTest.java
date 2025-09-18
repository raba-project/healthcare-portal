package com.healthcare.dao;

import com.healthcare.dao.DoctorDAO;
import com.healthcare.model.Doctor;
import com.healthcare.model.Role;
import com.healthcare.model.User;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DoctorDAOTest {
    DoctorDAO doctorDAO = new DoctorDAO();

    @Test
    public void testGetAllDoctors() throws Exception {
    	User u = new User(0, "doctoruser", "pass", "doctoruser@mail.com", "12345", Role.DOCTOR);
        new UserDAO().createUser(u);
        Doctor doctor = new Doctor(0, u.getUserId(), 1, "brain", "Available");
        doctorDAO.addDoctor(doctor);
        List<Doctor> list = doctorDAO.getAllDoctors();
        Assert.assertNotNull(list);
    }
}
