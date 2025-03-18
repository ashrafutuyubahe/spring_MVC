package com.example.clinic_mng_app.DAO;


import com.example.clinic_mng_app.Model.Patient;

import java.util.List;

public interface PatientDAO {

    void addPatient(Patient patient);

    List<Patient> getAllPatients();

    Patient getPatient(int id);

    void removePatient(int id);

    void updatePatient(Patient patient);

}
