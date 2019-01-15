package com.example.elvic.placetobe;

public class User {

    public String ortName;
    public int radius;
    public int miete;
    public int natur;
    public String aktivitaeten;
    public int aktivitaet;
    public String typ;


    public void setOrtName(String name){
        this.ortName = name;
    }
    public void setRadius(int radius){
        if (radius > 10 && radius <= 70) this.radius = radius;
    }
    public void setMiete(int miete){
        this.miete = miete;
    }
    public void setNatur(int natur){
        this.natur = natur;
    }
    public void setAktivitaeten(String aktivitaeten){
        this.aktivitaeten = aktivitaeten;
    }
    public void setAktivitaet(int aktivitaet){
        this.aktivitaet = aktivitaet;
    }
    public void setTyp(String typ){
        this.typ = typ;
    }

    public String getOrtName() {
        return ortName;
    }
    public int getRadius(){
        return radius;
    }
    public int getMiete(){
        return miete;
    }
    public int getNatur(){
        return natur;
    }
    public String getAktivitaeten(){
        return aktivitaeten;
    }
    public int getAktivitaet(){
        return aktivitaet;
    }
    public String getTyp(){
        return typ;
    }

    public User (String name, int radius, int miete, int natur, String aktivitaeten, int aktivitaet, String typ){
        this.ortName = name;
        this.radius = radius;
        this.miete = miete;
        this.natur = natur;
        this.aktivitaeten = aktivitaeten;
        this.aktivitaet = aktivitaet;
        this.typ = typ;
    }

}
