package com.example.projet_gerante_domergue.models;
import com.google.gson.annotations.SerializedName;

import java.util.Date;


public class Event {
    @SerializedName("id_evenement")
    private int idEvenement;

    @SerializedName("Nom_evenement")
    private String nomEvenement;

    @SerializedName("Description")
    private String description;

    @SerializedName("Date_evenement")
    private String dateEvenement;

    @SerializedName("Adresse")
    private String adresse;

    @SerializedName("nom_createur_evenement")
    private String nomCreateurEvenement;

    @SerializedName("prenom_createur_evenement")
    private String prenomCreateurEvenement;

    @SerializedName("email_createur_evenement")
    private String emailCreateurEvenement;

    @SerializedName("fonction_createur_evenement")
    private String fonctionCreateurEvenement;

    @SerializedName("ville_evenement")
    private String villeEvenement;

    @SerializedName("pays_evenement")
    private String paysEvenement;

    @SerializedName("participants")
    private String participants;

    // Constructeur
    public Event(int idEvenement, String nomEvenement, String description, String dateEvenement, String adresse, String nomCreateurEvenement, String prenomCreateurEvenement, String emailCreateurEvenement, String fonctionCreateurEvenement, String villeEvenement, String paysEvenement, String participants) {
        this.idEvenement = idEvenement;
        this.nomEvenement = nomEvenement;
        this.description = description;
        this.dateEvenement = dateEvenement;
        this.adresse = adresse;
        this.nomCreateurEvenement = nomCreateurEvenement;
        this.prenomCreateurEvenement = prenomCreateurEvenement;
        this.emailCreateurEvenement = emailCreateurEvenement;
        this.fonctionCreateurEvenement = fonctionCreateurEvenement;
        this.villeEvenement = villeEvenement;
        this.paysEvenement = paysEvenement;
        this.participants = participants;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    // Getters and setters
    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(String dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNomCreateurEvenement() {
        return nomCreateurEvenement;
    }

    public void setNomCreateurEvenement(String nomCreateurEvenement) {
        this.nomCreateurEvenement = nomCreateurEvenement;
    }

    public String getPrenomCreateurEvenement() {
        return prenomCreateurEvenement;
    }

    public void setPrenomCreateurEvenement(String prenomCreateurEvenement) {
        this.prenomCreateurEvenement = prenomCreateurEvenement;
    }

    public String getEmailCreateurEvenement() {
        return emailCreateurEvenement;
    }

    public void setEmailCreateurEvenement(String emailCreateurEvenement) {
        this.emailCreateurEvenement = emailCreateurEvenement;
    }

    public String getFonctionCreateurEvenement() {
        return fonctionCreateurEvenement;
    }

    public void setFonctionCreateurEvenement(String fonctionCreateurEvenement) {
        this.fonctionCreateurEvenement = fonctionCreateurEvenement;
    }

    public String getVilleEvenement() {
        return villeEvenement;
    }

    public void setVilleEvenement(String villeEvenement) {
        this.villeEvenement = villeEvenement;
    }

    public String getPaysEvenement() {
        return paysEvenement;
    }

    public void setPaysEvenement(String paysEvenement) {
        this.paysEvenement = paysEvenement;
    }

    public String getParticipants() {
        return participants;
    }
}
