package com.example.clinic_mng_app.ServiceImpl;

import com.example.clinic_mng_app.DAO.PatientDAO;
import com.example.clinic_mng_app.DAOImpl.PatientDAOImpl;
import com.example.clinic_mng_app.Model.Patient;
import com.example.clinic_mng_app.Service.PatientManagerService;

import java.sql.SQLException;
import java.util.List;

public class PatientManagementImpl implements PatientManagerService {

    private final PatientDAO patientDAO;

    public PatientManagementImpl() throws SQLException {
        this.patientDAO = new PatientDAOImpl();
    }

    @Override
    public void addPatient(String name, int age, String diagnosis) {
        Patient patient = new Patient();
        patient.setName(name);
        patient.setAge(age);
        patient.setDiagnosis(diagnosis);
        patientDAO.addPatient(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientDAO.getAllPatients();
    }

    @Override
    public void removePatient(int id) {
        patientDAO.removePatient(id);
    }

    @Override
    public void updatePatient(int id, String name, int age, String diagnosis) {
        Patient patient = new Patient();
        patient.setId(id);
        patient.setName(name);
        patient.setAge(age);
        patient.setDiagnosis(diagnosis);
        patientDAO.updatePatient(patient);
    }
}
