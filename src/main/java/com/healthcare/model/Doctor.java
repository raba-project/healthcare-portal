package com.healthcare.model;

public class Doctor {
    private int doctorId;
    private int userId;
    private int deptId;
    private String specialization;
    private String availability;

    public Doctor() {}

    public Doctor(int doctorId, int userId, int deptId, String specialization, String availability) {
        this.doctorId = doctorId;
        this.userId = userId;
        this.deptId = deptId;
        this.specialization = specialization;
        this.availability = availability;
    }

    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getDeptId() {
        return deptId;
    }
    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public String getAvailability() {
        return availability;
    }
    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
