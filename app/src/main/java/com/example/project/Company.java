package com.example.project;


public class Company {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;

    private String image;
    private String phone;
    private String facebookLink;

    // Constructor
    public Company(String name, String email, String password, String confirmPassword,String image, String phone, String facebookLink) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.image=image;
        this.phone = phone;
        this.facebookLink = facebookLink;
    }
    public Company(String name, String email) {
        this.name = name;
        this.email = email;

    }
    // Getters and Setters
    public  String getName() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", image='" + phone + '\'' +
                ", phone='" + phone + '\'' +
                ", facebookLink='" + facebookLink + '\'' +
                '}';
    }
}
