package com.example.myapplication;


public class Contact {
    private String name;
    private String phoneNumber;
    private int profileImage;

    public Contact(String name, String phoneNumber, int profileImage) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getProfileImage() {
        return profileImage;
    }
}
