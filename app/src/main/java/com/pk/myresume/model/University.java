package com.pk.myresume.model;

/**
 * Created by Purvesh on 3/8/2017.
 */

public class University {

    private String name;
    private String startYear;
    private String endYear;
    private String specialization;
    private String grade;
    private int logoId;

    public University(String name, String startYear, String endYear, String specialization, String grade,int logoId){
        this.name = name;
        this.startYear = startYear;
        this.endYear = endYear;
        this.specialization = specialization;
        this.grade = grade;
        this.logoId = logoId;
    }

    public String getName() {
        return name;
    }

    public String getStartYear() {
        return startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getGrade() {
        return grade;
    }

    public int getLogoId() { return logoId; }
}
