package com.example.blooddonnation;

public class Request {
    String email, tel, blood_type, city,content;

    public Request(String email, String tel, String blood_type, String city, String content) {
        this.email = email;
        this.tel = tel;
        this.blood_type = blood_type;
        this.city = city;
        this.content = content;
    }

    public Request() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
