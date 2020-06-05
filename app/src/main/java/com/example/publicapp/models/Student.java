package com.example.publicapp.models;

public class Student {
    private String courseID;
    private String email;
    private String lastName;
    private String name;
    private String password;
    private String user;

    public Student(){

    }

    public Student(String courseID, String email, String lastName, String name, String password, String user) {
        this.courseID = courseID;
        this.email = email;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
        this.user = user;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return courseID;
    }
}
