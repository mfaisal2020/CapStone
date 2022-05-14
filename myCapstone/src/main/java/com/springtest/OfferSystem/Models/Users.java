package com.springtest.OfferSystem.Models;

import javax.persistence.*;

@Entity
public class Users {

    @Id
    private String userName;

    @Column
    private String deviceID;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

}
