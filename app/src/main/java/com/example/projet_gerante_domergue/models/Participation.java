package com.example.projet_gerante_domergue.models;

public class Participation {
    private String Nom_evenement;
    private String Description;
    private String Date_evenement;
    private String Nom_createur;
    private String Prenom_createur;

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

    public String getNom_createur() {
        return Nom_createur;
    }

    public void setNom_createur(String nom_createur) {
        this.Nom_createur = nom_createur;
    }

    public String getPrenom_createur() {
        return Prenom_createur;
    }

    public void setPrenom_createur(String prenom_createur) {
        this.Prenom_createur = prenom_createur;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "Nom_evenement='" + Nom_evenement + '\'' +
                ", Description='" + Description + '\'' +
                ", Date_evenement='" + Date_evenement + '\'' +
                ", Nom_createur='" + Nom_createur + '\'' +
                ", Prenom_createur='" + Prenom_createur + '\'' +
                '}';
    }
}
