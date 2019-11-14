package com.example.microhousingsystem;

public class HousingOfficer extends User{

    private String staffID;
    private String userType;

    static int number =1;


    public HousingOfficer() {

    }

    public HousingOfficer(String id,String username, String password, String fullname) {
        super(id,username, password, fullname);
        setStaffID();
        setUserType();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType() {
        this.userType = "HousingOfficer";
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID() {
        this.staffID = staffID + ++number;
    }
}
