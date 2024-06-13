package com.example.studentmanager.models;

public class Student {

    int id ;
    String name, email, major;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String username) {
        this.name = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMajor() {
        return major;
    }

    public Student(int id, String name, String email, String major) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.major = major;
    }

}

