package com.example.project;

public class User {
    private String Email ;
    private String pass ;
    private String Name;
    private char gender ;
    private String city ;

    public User(String email, String pass, String name, char gender, String city) {
        Email = email;
        this.pass = pass;
        Name = name;
        this.gender = gender;
        this.city = city;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
