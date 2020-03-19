package com.example.onlinetestexam;

public class EeeDataModel {

    private String CourseName;
    public  EeeDataModel()
    {

    }

    public EeeDataModel(String courseName) {
        CourseName = courseName;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }
}
