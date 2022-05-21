package com.springtest.OfferSystem.Models;

import javax.persistence.*;

@Entity
public class AllRequests {

    @Id
    private String prodID;

    @Column
    private double b_marketPrice;

    @Column
    private double c_requestedPrice;

    @Column
    private long d_locationZIP;

    @Column
    private String e_productDemand;

    @Column
    private long f_nbrofReq;

    @Column
    private long g_reqCredibility;

    @Column
    private String h_offerByOthers;

    @Column
    private long i_revenueOpportunity;

    @Column
    private String j_merchantAlignments;

    @Column
    private String k_basicVsNon;

    @Column
    private String l_last12mntsOffer;

    @Column
    private long m_decision;


    public String getProdID() {
        return prodID;
    }

    public void setProdID(String prodID) { this.prodID = prodID; }

    public double getMarketPrice() {
        return b_marketPrice;
    }

    public void setMarketPrice(double b_marketPrice) {
        this.b_marketPrice = b_marketPrice;
    }

    public double getRequestedPrice() {
        return c_requestedPrice;
    }

    public void setRequestedPrice(double c_requestedPrice) {
        this.c_requestedPrice = c_requestedPrice;
    }

    public long getLocationZip() {
        return d_locationZIP;
    }

    public void setLocationZIP(long d_locationZIP) {
        this.d_locationZIP = d_locationZIP;
    }

    public String getProductDemand() {
        return e_productDemand;
    }

    public void setProductDemand(String e_productDemand) { this.e_productDemand = e_productDemand; }

    public long getNbrofReq() {
        return f_nbrofReq;
    }

    public void setNbrofReq(long f_nbrofReq) {
        this.f_nbrofReq = f_nbrofReq;
    }

    public long getReqCredibility() {
        return g_reqCredibility;
    }

    public void setReqCredibility(long g_reqCredibility) {
        this.g_reqCredibility = g_reqCredibility;
    }

    public String getOfferByOthers() {
        return h_offerByOthers;
    }

    public void setOfferByOthers(String h_offerByOthers) { this.h_offerByOthers = h_offerByOthers; }

    public long getRevenueOpportunity() {
        return i_revenueOpportunity;
    }

    public void setRevenueOpportunity(long i_revenueOpportunity) { this.i_revenueOpportunity = i_revenueOpportunity; }

    public String getMerchantAlignments() {
        return j_merchantAlignments;
    }

    public void setMerchantAlignments(String j_merchantAlignments) { this.j_merchantAlignments = j_merchantAlignments; }

    public String getBasicVsNon() {
        return k_basicVsNon;
    }

    public void setBasicVsNon(String k_basicVsNon) { this.k_basicVsNon = k_basicVsNon; }

    public String getLast12mntsOffer() {
        return l_last12mntsOffer;
    }

    public void setLast12mntsOffer(String l_last12mntsOffer) { this.l_last12mntsOffer = l_last12mntsOffer; }

    public long getDecision() {
        return m_decision;
    }

    public void setDecision(long m_decision) { this.m_decision = m_decision; }
}
