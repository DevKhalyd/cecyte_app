package com.khalyd.cecyteapp.Models;

/**
 * Created by Rolando Garcia on 04/02/2018.
 */

public class Directivo {

    private String name;
    private String lastname;
    private String url;
    private String cargo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
