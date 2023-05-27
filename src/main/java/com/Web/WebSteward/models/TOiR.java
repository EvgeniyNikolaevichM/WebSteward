package com.Web.WebSteward.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TOiR {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String KlassOborud;
    private String VidRabot;

    private String Date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKlassOborud() {
        return KlassOborud;
    }

    public void setKlassOborud(String klassOborud) {
        KlassOborud = klassOborud;
    }

    public String getVidRabot() {
        return VidRabot;
    }

    public void setVidRabot(String vidRabot) {
        VidRabot = vidRabot;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public TOiR() {
    }

    public TOiR(String klassOborud, String vidRabot, String date) {
        KlassOborud = klassOborud;
        VidRabot = vidRabot;
        Date = date;
    }
}

