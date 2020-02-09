package com.example.assignment2;

public class AssignmentClass {

    int id;
    int courseid;
    String name;
    int grade;

    public AssignmentClass() {
    }

    ;

    public AssignmentClass(int id, int courseid, String name, int grade) {
        this.id = id;
        this.courseid = courseid;
        this.name = name;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
