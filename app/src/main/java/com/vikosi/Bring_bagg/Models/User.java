package com.vikosi.Bring_bagg.Models;

public class User {
    private String name, email, pass, phone, birth;
    public User() {};

    public User(String name, String email, String pass, String phone, String birth) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.phone = phone;
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
