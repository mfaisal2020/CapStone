package com.springtest.OfferSystem.Models;

import javax.persistence.*;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long usrID;

    @Column
    private String userName;

    @Column
    private String deviceID;

    public long getusrID() {
        return usrID;
    }

    public void setusrID(long usrID) {
        this.usrID = usrID;
    }

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
