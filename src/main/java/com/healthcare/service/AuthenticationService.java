package com.healthcare.service;

import java.sql.SQLException;

import com.healthcare.dao.UserDAO;
import com.healthcare.model.Role;
import com.healthcare.model.User;

public class AuthenticationService {
    private UserDAO userDAO;
    
    public AuthenticationService() throws SQLException {
    	this.userDAO = new UserDAO();
    }
    
    public User login(String username, String password) throws Exception {
        User u = userDAO.findByUsername(username);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        throw new Exception("Invalid credentials!");
    }

    public void register(User user) throws Exception {
        userDAO.createUser(user);
    }
}
