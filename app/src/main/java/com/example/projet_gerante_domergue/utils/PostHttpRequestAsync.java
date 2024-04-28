package com.example.projet_gerante_domergue.utils;

import android.os.AsyncTask;
import android.util.Pair;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostHttpRequestAsync extends AsyncTask<String, Void, Pair<Integer, String>> {

    private static final String BASE_URL = "http://10.0.2.2:3000"; // URL de votre API

    @Override
    protected Pair<Integer, String> doInBackground(String... params) {
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

            int responseCode = connection.getResponseCode();
            String jsonResponse = readResponse(connection.getInputStream());

            return new Pair<>(responseCode, jsonResponse); // Renvoyer le code de statut et la réponse JSON
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String readResponse(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        return response.toString();
    }
}

