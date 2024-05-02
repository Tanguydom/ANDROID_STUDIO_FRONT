package com.example.projet_gerante_domergue.models;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("Id_ville")
    private int id;
    @SerializedName("Nom_ville")
    private String name;
    @SerializedName("Pays")
    private String country;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return name;
    }
}
