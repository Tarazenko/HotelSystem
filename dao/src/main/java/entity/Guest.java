package entity;

import java.util.List;

public class Guest {

    private int id;
    private String phoneNumber;
    private double bill;
    private Passport passport;
    private List<Attendance> attendances;

    public Guest() {
    }

    public Guest(int guestId, String phoneNumber, double bill, List<Attendance> attendances) {
        this.id = guestId;
        this.phoneNumber = phoneNumber;
        this.bill = bill;
        this.attendances = attendances;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bill=" + bill +
                ",\nattendance=" + attendances +
                ",\npassport = " + passport +
                "}\n";
    }

}
