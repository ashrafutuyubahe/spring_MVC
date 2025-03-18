package com.example.clinic_mng_app.DAOImpl;

import com.example.clinic_mng_app.DAO.PatientDAO;
import com.example.clinic_mng_app.Model.Patient;
import com.example.clinic_mng_app.Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {

    private final Connection dbConn = DBConnection.getConnection();

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
//                System.out.println("here are patiens");
//                System.out.println(patients);
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
            System.out.println(patient);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
