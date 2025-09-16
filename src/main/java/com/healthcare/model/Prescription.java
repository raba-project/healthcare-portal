package com.healthcare.model;

public class Prescription {
    private int prescriptionId;
    private int appointmentId;
    private String doctorNotes;
    private String medicines;

    public Prescription() {}

    public Prescription(int prescriptionId, int appointmentId, String doctorNotes, String medicines) {
        this.prescriptionId = prescriptionId;
        this.appointmentId = appointmentId;
        this.doctorNotes = doctorNotes;
        this.medicines = medicines;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }
    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }
    public int getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
    public String getDoctorNotes() {
        return doctorNotes;
    }
    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }
    public String getMedicines() {
        return medicines;
    }
    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }
}
