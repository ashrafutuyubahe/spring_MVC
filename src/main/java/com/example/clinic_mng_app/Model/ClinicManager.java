package com.example.clinic_mng_app.Model;



public class ClinicManager {



    private  String clinicManagerName;
    private String clinicManagerPassword;
    private  String clinicManagerEmail;



    public ClinicManager(String clinicManagerName, String clinicManagerPassword, String clinicManagerEmail) {
        this.clinicManagerName = clinicManagerName;
        this.clinicManagerPassword = clinicManagerPassword;
        this.clinicManagerEmail = clinicManagerEmail;
    }

    public ClinicManager() {

    }

    public ClinicManager(int clinic_manager_id, String clinic_manager_name, String email) {

    }

    public String getClinicManagerName() {
        return clinicManagerName;
    }

    public void setClinicManagerName(String clinicManagerName) {
        this.clinicManagerName = clinicManagerName;
    }

    public String getClinicManagerPassword() {
        return clinicManagerPassword;
    }

    public void setClinicManagerPassword(String clinicManagerPassword) {
        this.clinicManagerPassword = clinicManagerPassword;
    }

    public String getClinicManagerEmail() {
        return clinicManagerEmail;
    }

    public void setClinicManagerEmail(String clinicManagerEmail) {
        this.clinicManagerEmail = clinicManagerEmail;
    }
    @Override
    public String toString() {
        return "ClinicManager{" +
                "clinicManagerName='" + clinicManagerName + '\'' +
                ", clinicManagerPassword='" + clinicManagerPassword + '\'' +
                ", clinicManagerEmail='" + clinicManagerEmail + '\'' +
                '}';
    }


}
