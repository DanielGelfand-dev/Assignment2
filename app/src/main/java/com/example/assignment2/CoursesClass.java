package com.example.assignment2;

public class CoursesClass {

    int id;

    String title;
    String code;
    int average;


    public CoursesClass() {
    }

    ;

    public CoursesClass(int id, String title, String code, int average) {
        this.id = id;

        this.title = title;
        this.code = code;
        this.average = average;
    }


    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
