package com.example.rago2.Models;

public class Booking_UsersMember {
    String name,address,landArea,uid,phoneNo,url,croptype;
 public Booking_UsersMember(){

 }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCroptype(String croptype) {
        return this.croptype;
    }

    public void setCroptype(String croptype) {
        this.croptype = croptype;
    }
}

