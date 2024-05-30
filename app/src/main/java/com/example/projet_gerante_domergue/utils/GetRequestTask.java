package com.example.projet_gerante_domergue.utils;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequestTask extends AsyncTask<String, Void, String> // héritage de AsyncTask pour effectuer une requête HTTP en arrière-plan
{
    private static final String BASE_URL = "https://android.karru.tech"; // URL de votre API

    @Override
    protected String doInBackground(String... params) // params[0] est l'endpoint
    {
        String endpoint = params[0]; // endpoint de l'API
        try {
            URL url = new URL(BASE_URL + endpoint); // URL de l'API
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // Ouverture de la connexion
            connection.setRequestMethod("GET"); // Méthode HTTP GET
            return readResponse(connection); // Lecture de la réponse
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String readResponse(HttpURLConnection connection) throws IOException // Lecture de la réponse
    {
        StringBuilder response = new StringBuilder(); // Réponse de l'API
        try (InputStream inputStream = connection.getInputStream(); // Flux d'entrée
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) // Buffer de lecture
        {
            String line;// Ligne de la réponse
            while ((line = bufferedReader.readLine()) != null) // Lecture de la réponse
            {
                response.append(line);// Ajout de la ligne à la réponse
            }
        }
        return response.toString();
    }
}