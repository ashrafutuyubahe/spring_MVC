package com.example.clinic_mng_app.Model;

public class Patient {

    private int id;
    private String name;
    private int age;
    private String email;
    private String diagnosis;

    // Constructor, getters, and setters
    public Patient(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;

    }




    public Patient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }




    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }

    public String getDiagnosis() {
      return  this.diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis=diagnosis;
    }
}
