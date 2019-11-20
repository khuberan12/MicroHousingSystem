package com.example.microhousingsystem;

public class HousingOfficer extends User{

    private String userType;




    public HousingOfficer() {

    }
    public HousingOfficer(String id,String username, String password){
        super(id,username,password);
    }


    public HousingOfficer(String id,String username, String password, String fullname) {
        super(id,username, password, fullname);
        this.userType ="houseOfficer";
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType() {
        this.userType = "HousingOfficer";
    }


}
