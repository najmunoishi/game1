package com.example.onlinetestexam;

public class Client {

    String name;
    String Phone;
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Client(String email) {
        this.email = email;
    }

    public Client(String name, String phone) {
        this.name = name;

        Phone = phone;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
