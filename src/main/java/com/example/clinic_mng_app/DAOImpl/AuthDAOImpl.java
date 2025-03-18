package com.example.clinic_mng_app.DAOImpl;


import com.example.clinic_mng_app.DAO.AuthDAO;
import com.example.clinic_mng_app.DAO.PatientDAO;
import com.example.clinic_mng_app.Model.ClinicManager;
import com.example.clinic_mng_app.Model.Patient;
import com.example.clinic_mng_app.Util.DBConnection;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class AuthDAOImpl implements AuthDAO {
    private final Connection dbConn;

    public AuthDAOImpl() throws SQLException {
        dbConn = DBConnection.getConnection();
    }

    // Register a new user (Clinic Manager)
    @Override
    public boolean userRegister(ClinicManager clinicManager) {
        String query = "INSERT INTO clinic_manager (clinic_manager_name, clinic_manager_password, clinic_manager_email) VALUES (?, ?, ?)";
        try (PreparedStatement ps = dbConn.prepareStatement(query)) {
            ps.setString(1, clinicManager.getClinicManagerName());
            ps.setString(2, hashPassword(clinicManager.getClinicManagerPassword()));
            ps.setString(3, clinicManager.getClinicManagerEmail());
            return ps.executeUpdate() > 0; // Return true if row is inserted
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Authenticate user login based on email and password
    @Override
    public ClinicManager userLogin(String email, String password) {
        String query = "SELECT clinic_manager_id, clinic_manager_name, clinic_manager_email FROM clinic_manager WHERE clinic_manager_email = ? AND clinic_manager_password = ?";
        try (PreparedStatement ps = dbConn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, hashPassword(password)); // Hash the password before checking it
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ClinicManager(rs.getInt("clinic_manager_id"), rs.getString("clinic_manager_name"), email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if user not found or invalid credentials
    }

    // Logout functionality (clear session)
    @Override
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

    }


    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public static class PatientDAOImpl implements PatientDAO {

        Connection dbConn = DBConnection.getConnection();

        public PatientDAOImpl() throws SQLException {
        }

        @Override
        public void addPatient(Patient patient) {
            String query = "INSERT INTO patients (name, age, diagnosis) VALUES (?, ?, ?)";
            try (PreparedStatement ps = dbConn.prepareStatement(query)) {
                ps.setString(1, patient.getName());
                ps.setInt(2, patient.getAge());
                ps.setString(3, patient.getDiagnosis());
                ps.executeUpdate();
                System.out.println(patient);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public List<Patient> getAllPatients() {
            List<Patient> patients = new ArrayList<>();
            String query = "SELECT * FROM patients";
            try (PreparedStatement ps = dbConn.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    Patient patient = new Patient();
                    patient.setId(rs.getInt("id"));
                    patient.setName(rs.getString("name"));
                    patient.setAge(rs.getInt("age"));
                    patient.setDiagnosis(rs.getString("diagnosis"));
                    patients.add(patient);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return patients;
        }

        @Override
        public Patient getPatient(int id) {
            Patient patient = null;
            String query = "SELECT * FROM patients WHERE id = ?";
            try (PreparedStatement ps = dbConn.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    patient = new Patient();
                    patient.setId(rs.getInt("id"));
                    patient.setName(rs.getString("name"));
                    patient.setAge(rs.getInt("age"));
                    patient.setDiagnosis(rs.getString("diagnosis"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return patient;
        }

        @Override
        public void removePatient(int id) {
            String query = "DELETE FROM patients WHERE id = ?";
            try (PreparedStatement ps = dbConn.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void updatePatient(Patient patient) {
            String query = "UPDATE patients SET name = ?, age = ?, diagnosis = ? WHERE id = ?";
            try (PreparedStatement ps = dbConn.prepareStatement(query)) {
                ps.setString(1, patient.getName());
                ps.setInt(2, patient.getAge());
                ps.setString(3, patient.getDiagnosis());
                ps.setInt(4, patient.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
