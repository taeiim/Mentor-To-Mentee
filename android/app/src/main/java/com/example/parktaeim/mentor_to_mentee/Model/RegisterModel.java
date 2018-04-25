package com.example.parktaeim.mentor_to_mentee.Model;

public class RegisterModel {
    private final String name, id, pw, pwConfirm, phoneCall, email, age, job;

    public RegisterModel(String name, String id, String pw, String pwConfirm, String phoneCall, String email, String age, String job) {
        this.name = name;
        this.id = id;
        this.pw = pw;
        this.pwConfirm = pwConfirm;
        this.phoneCall = phoneCall;
        this.email = email;
        this.age = age;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getPwConfirm() {
        return pwConfirm;
    }

    public String getPhoneCall() {
        return phoneCall;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    public String getJob() {
        return job;
    }
}
