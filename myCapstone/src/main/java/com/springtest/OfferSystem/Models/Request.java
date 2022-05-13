package com.springtest.OfferSystem.Models;

import javax.persistence.*;

@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reqID;

    @Column
    private String userName;

    @Column
    private String deviceID;

    @Column
    private String productID;

    public long getreqID() {
        return reqID;
    }

    public void setreqID(long reqID) {
        this.reqID = reqID;
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

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
}
