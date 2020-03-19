package com.example.onlinetestexam;

public class CseDataModel {

   private String CourseName;
   public  CseDataModel()
   {

   }

    public CseDataModel(String courseName) {
        CourseName = courseName;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }
}
