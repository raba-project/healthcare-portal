package com.healthcare.dao;

import com.healthcare.dao.UserDAO;
import com.healthcare.model.Role;
import com.healthcare.model.User;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserDAOTest {
    UserDAO userDAO;
    public UserDAOTest() throws SQLException {
    	this.userDAO = new UserDAO();
    }
    @Test
    public void testAddAndGetUser() throws Exception {
        User u = new User(0, "testuser", "pass", "test@mail.com", "12345", Role.PATIENT);
        userDAO.createUser(u);
        User fetched = userDAO.findByUsername("testuser");
        Assert.assertEquals(fetched.getUsername(), "testuser");
    }
}
