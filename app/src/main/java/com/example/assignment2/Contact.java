package com.example.assignment2;

public class Contact {
    private String contactName;
    private String contactNumber;
    private int iconIndex;

    public int getIconIndex() {
        return iconIndex;
    }

    public void setIconIndex(int iconIndex) {
        this.iconIndex = iconIndex;
    }

    public Contact(String contactName, String contactNumber, int iconIndex) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.iconIndex = iconIndex;
    }

    public Contact(){}

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
