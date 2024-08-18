package com.example.assignment2;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Contact implements Serializable {
    private int listIndex;

    public int getListIndex() {
        return listIndex;
    }

    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }

    private String contactName;
    private String contactNumber;
    private int imageResourceID;
    public int getImageResourceID() {
        return imageResourceID;
    }

    public void setImageResourceID(int imageResourceID) {
        this.imageResourceID = imageResourceID;
    }

    public Contact(String contactName, String contactNumber, int imageResourceID) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.imageResourceID = imageResourceID;
    }
    public Contact(String contactName, String contactNumber, int imageResourceID, int listIndex){
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.imageResourceID = imageResourceID;
        this.listIndex = listIndex;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        Contact compared = (Contact) obj;
        return contactName.equals(compared.getContactName())&& contactNumber.equals(compared.getContactNumber())
                && imageResourceID == compared.getImageResourceID();
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
