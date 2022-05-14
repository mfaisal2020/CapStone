package com.springtest.OfferSystem.Models;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    private String prodID;

    @Column
    private String prodDesc;

    @Column
    private double marketPrice;

    @Column
    private double requestPrice;

    public String getProdID() {
        return prodID;
    }

    public void setProdID(String prodID) { this.prodID = prodID; }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) { this.prodDesc = prodDesc; }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public double getRequestPrice() {
        return requestPrice;
    }

    public void setRequestPrice(double requestPrice) {
        this.requestPrice = requestPrice;
    }

}
