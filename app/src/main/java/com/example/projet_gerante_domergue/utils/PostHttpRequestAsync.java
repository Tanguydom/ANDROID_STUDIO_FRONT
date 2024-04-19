package com.example.projet_gerante_domergue.utils;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostHttpRequestAsync extends AsyncTask<String, Void, Integer> {

    private static final String BASE_URL = "http://10.0.2.2:3000"; // URL de votre API

    @Override
    protected Integer doInBackground(String... params) {
        String endpoint = params[0];
        String postData = params[1];
        try {
            URL url = new URL(BASE_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.writeBytes(postData);
            }
            return connection.getResponseCode(); // Renvoyer le code de statut de la requête
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
