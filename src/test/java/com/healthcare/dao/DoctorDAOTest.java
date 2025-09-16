package com.healthcare.dao;

import com.healthcare.dao.DoctorDAO;
import com.healthcare.model.Doctor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DoctorDAOTest {
    DoctorDAO doctorDAO = new DoctorDAO();

    @Test
    public void testGetAllDoctors() throws Exception {
        List<Doctor> list = doctorDAO.getAllDoctors();
        Assert.assertNotNull(list);
    }
}
