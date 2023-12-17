package com.example.blooddonnation;

public class User {
    String email, phone, password, city, bloodgrp;

    public User() {
    }

    public User(String email, String phone, String password, String city, String bloodgrp) {
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.city = city;
        this.bloodgrp = bloodgrp;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getCity() {
        return city;
    }

    public String getBloodgrp() {
        return bloodgrp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBloodgrp(String bloodgrp) {
        this.bloodgrp = bloodgrp;
    }
}
