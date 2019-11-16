package com.example.microhousingsystem;

public class Applicant extends User {


    private String email;
    private String monthlyIncome;
    private String userType;


    public Applicant() {
    }

    public Applicant(String id,String username, String password){
        super(id,username,password);
    }

    public Applicant(String email, String monthlyIncome) {
        this.email = email;
        this.monthlyIncome = monthlyIncome;
        this.userType ="applicant";
    }



    public Applicant(String id,String username, String password, String fullname, String email, String monthlyIncome) {
        super(id,username, password, fullname);
        this.email = email;
        this.monthlyIncome = monthlyIncome;
        this.userType ="applicant";
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType() {
        this.userType = "applicant";
    }

}
