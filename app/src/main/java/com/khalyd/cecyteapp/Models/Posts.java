package com.khalyd.cecyteapp.Models;


public class Posts {

    //Al parecer esto lo estan ocupando la mayoria de perfiles

    private String description;
    private String url; //imageReferenciada o sea la del post
    private String title;
    private String nameDirectivo;
    private String urlDirectivo;
    private String position;
    private String profile;//Los numeros con los q se identifiacan los perfiles
    private String datepots;

    public String getDatepots() {
        return datepots;
    }

    public void setDatepots(String datepots) {
        this.datepots = datepots;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNameDirectivo() {
        return nameDirectivo;
    }

    public void setNameDirectivo(String nameDirectivo) {
        this.nameDirectivo = nameDirectivo;
    }

    public String getUrlDirectivo() {
        return urlDirectivo;
    }

    public void setUrlDirectivo(String urlDirectivo) {
        this.urlDirectivo = urlDirectivo;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
