package com.example.clinic_mng_app.Service;

    import jakarta.servlet.http.HttpServletRequest;

    public interface AuthenticationService {

        void userRegister();
        void userLogin();
        void logout( HttpServletRequest request);
    }
