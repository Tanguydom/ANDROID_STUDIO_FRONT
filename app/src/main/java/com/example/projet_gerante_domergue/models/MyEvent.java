package com.example.projet_gerante_domergue.models;

public class MyEvent {
    private String Nom_evenement;
    private String Description;
    private String Date_evenement;

    public String getNom_evenement() {
        return Nom_evenement;
    }

    public void setNom_evenement(String nom_evenement) {
        this.Nom_evenement = nom_evenement;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getDate_evenement() {
        return Date_evenement;
    }

    public void setDate_evenement(String date_evenement) {
        this.Date_evenement = date_evenement;
    }
}
