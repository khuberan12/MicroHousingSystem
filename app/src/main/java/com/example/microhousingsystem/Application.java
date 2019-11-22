package com.example.microhousingsystem;

public class Application {
    private String idApp;
    private String applicationDate;
    private String requiredMonth;
    private String requiredYear;
    private String status;

    public Application() {
    }

    public Application(String idApp, String applicationDate, String requiredMonth, String requiredYear, String status) {
        this.idApp = idApp;
        this.applicationDate = applicationDate;
        this.requiredMonth = requiredMonth;
        this.requiredYear = requiredYear;
        this.status = status;
    }

    public String getIdApp() {
        return idApp;
    }

    public void setIdApp(String idApp) {
        this.idApp = idApp;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getRequiredMonth() {
        return requiredMonth;
    }

    public void setRequiredMonth(String requiredMonth) {
        this.requiredMonth = requiredMonth;
    }

    public String getRequiredYear() {
        return requiredYear;
    }

    public void setRequiredYear(String requiredYear) {
        this.requiredYear = requiredYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Application{" +
                "idApp='" + idApp + '\'' +
                ", applicationDate='" + applicationDate + '\'' +
                ", requiredMonth='" + requiredMonth + '\'' +
                ", requiredYear='" + requiredYear + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
