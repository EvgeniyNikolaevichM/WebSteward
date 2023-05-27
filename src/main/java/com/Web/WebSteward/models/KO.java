package com.Web.WebSteward.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class KO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String klassNapr;
    private String dispNaim;
    private String PO;
    private String SP;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKlassNapr() {
        return klassNapr;
    }

    public void setKlassNapr(String klassNapr) {
        this.klassNapr = klassNapr;
    }

    public String getDispNaim() {
        return dispNaim;
    }

    public void setDispNaim(String dispNaim) {
        this.dispNaim = dispNaim;
    }

    public String getPO() {
        return PO;
    }

    public void setPO(String PO) {
        this.PO = PO;
    }

    public String getSP() {
        return SP;
    }

    public void setSP(String SP) {
        this.SP = SP;
    }

    public KO() {
    }

    public KO(String title, String klassNapr, String dispNaim, String PO, String SP) {
        this.title = title;
        this.klassNapr = klassNapr;
        this.dispNaim = dispNaim;
        this.PO = PO;
        this.SP = SP;
    }
}
