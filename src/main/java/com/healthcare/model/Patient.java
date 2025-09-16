package com.healthcare.model;

public class Patient {
    private int patientId;
    private int userId;
    private int age;
    private String gender;
    private String bloodGroup;

    public Patient() {}

    public Patient(int patientId, int userId, int age, String gender, String bloodGroup) {
        this.patientId = patientId;
        this.userId = userId;
        this.age = age;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
    }

    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
}
