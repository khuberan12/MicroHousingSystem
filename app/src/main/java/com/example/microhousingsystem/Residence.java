package com.example.microhousingsystem;

public class Residence {

    private String residenceID;
    private String address;
    private String numOfUnits;
    private String sizePerUnit;
    private String monthlyRental;

    public Residence (String residenceID, String address, String numOfUnits, String sizePerUnit, String monthlyRental) {
        this.residenceID = residenceID;
        this.address = address;
        this.numOfUnits = numOfUnits;
        this.sizePerUnit = sizePerUnit;
        this.monthlyRental = monthlyRental;
    }

    public Residence() {
    }

    public String getResidenceID() {
        return residenceID;
    }

    public void setResidenceID(String residenceID) {
        this.residenceID = residenceID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumOfUnits() {
        return numOfUnits;
    }

    public void setNumOfUnits(String numOfUnits) {
        this.numOfUnits = numOfUnits;
    }

    public String getSizePerUnit() {
        return sizePerUnit;
    }

    public void setSizePerUnit(String sizePerUnit) {
        this.sizePerUnit = sizePerUnit;
    }

    public String getMonthlyRental() {
        return monthlyRental;
    }

    public void setMonthlyRental(String monthlyRental) {
        this.monthlyRental = monthlyRental;
    }

    @Override
    public String toString() {
        return "Residence{" +
                "residenceID='" + residenceID + '\'' +
                ", address='" + address + '\'' +
                ", numOfUnits='" + numOfUnits + '\'' +
                ", sizePerUnit='" + sizePerUnit + '\'' +
                ", monthlyRental='" + monthlyRental + '\'' +
                '}';
    }
}
