package com.khalyd.cecyteapp.Models;


public class Materias {

    private String subject;
    private String teach;
    private int background;

    //Esto sirve para pedir cosas por ejemplo (?)

    public Materias(String subject, String teach, int background) {
        this.subject = subject;
        this.teach = teach;
        this.background = background;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeach() {
        return teach;
    }

    public void setTeach(String teach) {
        this.teach = teach;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}

