
package com.example.clinic_mng_app.DAO;

import com.example.clinic_mng_app.Model.ClinicManager;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthDAO {
    boolean userRegister(ClinicManager clinicManager);
    ClinicManager userLogin(String email, String password);
     void logout(HttpServletRequest request);


}

