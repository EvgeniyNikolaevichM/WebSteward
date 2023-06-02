package com.Web.WebSteward.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TOiR")
public class TOiR {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Класс оборудования не должен быть пустым")
    @Size(min = 2, max = 100, message = "Класс оборудования должен быть от 2 до 100 символов длиной")
    @Column(name = "KlassOborud")
    private String KlassOborud;

    @NotEmpty(message = "Вид работ не должен быть пустым")
    @Size(min = 2, max = 100, message = "Вид работ должен быть быть от 2 до 100 символов длиной")
    @Column(name = "VidRabot")
    private String VidRabot;

    @NotEmpty(message = "Дата не должна быть пустой")
    @Column(name = "Date")
    @Pattern(
            regexp = "[0-9][0-9]\\.[0-9][0-9]\\.[0-9][0-9][0-9][0-9]",
            message = "Не соответствует формату дата: ДД.ММ.ГГГГ")
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

