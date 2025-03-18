package com.example.clinic_mng_app.ServiceImpl;


import com.example.clinic_mng_app.DAO.AuthDAO;
import com.example.clinic_mng_app.DAOImpl.AuthDAOImpl;
import com.example.clinic_mng_app.Model.ClinicManager;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;

public class AuthServiceImpl {
    private final AuthDAO authDAO;

    public AuthServiceImpl() throws SQLException {
        this.authDAO = new AuthDAOImpl();
    }

    // Register user
    public boolean registerUser(ClinicManager clinicManager) {
        return authDAO.userRegister(clinicManager);
    }

    // Authenticate user login
    public ClinicManager authenticateUser(String email, String password) {
        return authDAO.userLogin(email, password); // Call DAO for authentication
    }

    // Logout functionality
    public void logout(HttpServletRequest request) {
        authDAO.logout(request); // Call DAO to invalidate session
    }
}
