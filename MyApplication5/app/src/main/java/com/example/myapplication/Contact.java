package com.example.myapplication;


public class Contact {
    private String name;
    private String phoneNumber;
    private int imageResId;

    public Contact(String name, String phoneNumber, int imageResId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getImageResId() {
        return imageResId;
    }
}
