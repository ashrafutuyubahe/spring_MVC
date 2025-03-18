package com.example.clinic_mng_app.Service;


import com.example.clinic_mng_app.Model.Patient;

import java.util.List;

public interface PatientManagerService {


       List<Patient> getAllPatients();


       void removePatient(int id);


      void addPatient(String name, int parseInt, String diagnosis);

       void updatePatient(int parseInt, String name, int parseInt1, String diagnosis);
}
