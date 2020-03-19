package com.example.onlinetestexam;

import android.widget.ImageView;

public class DataModel {

    private String CourseName;
    private String Image;
    public DataModel(){}

    public DataModel(String courseName, String image) {
        CourseName = courseName;
        Image = image;
    }



    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }
}
