package com.example.projet_gerante_domergue.models;

public class User {
    private int Id_utilisateur;
    private String Nom;
    private String Prenom;
    private String Email;
    private String Mot_de_passe;
    private String fonction;
    private String longitude;
    private String latitude;

    // Constructeur par défaut requis pour Gson
    public User() {}

    // Getters et setters
    public int getId_utilisateur() {
        return Id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        Id_utilisateur = id_utilisateur;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMot_de_passe() {
        return Mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        Mot_de_passe = mot_de_passe;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
