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

    @Column
    private long zipCode;

    @Column
    private Double requestedPrice;

    @Column
    private String reqFlag;

    @Column
    private String reqDecision;

    public long getReqID() {
        return reqID;
    }

    public void setReqID(long reqID) {
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

    public long getZipCode() {
        return zipCode;
    }

    public void setZipCode(long zipCode) {
        this.zipCode = zipCode;
    }

    public Double getRequestedPrice() {
        return requestedPrice;
    }

    public void setRequestedPrice(Double requestedPrice) {
        this.requestedPrice = requestedPrice;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getReqFlag() {
        return reqFlag;
    }

    public void setReqFlag(String reqFlag) {
        this.reqFlag = reqFlag;
    }

    public String getReqDecision() {
        return reqDecision;
    }

    public void setReqDecision(String reqDecision) {
        this.reqDecision = reqDecision;
    }
}
