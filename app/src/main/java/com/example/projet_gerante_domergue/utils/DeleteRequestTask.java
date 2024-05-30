package com.example.projet_gerante_domergue.utils;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteRequestTask extends AsyncTask<String, Void, String> {
    private static final String BASE_URL = "https://android.karru.tech"; // URL de votre API
    @Override
    protected String doInBackground(String... params) {
        String endpoint = params[0];
        try {
            URL url = new URL(BASE_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            return readResponse(connection);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String readResponse(HttpURLConnection connection) throws IOException {
        StringBuilder response = new StringBuilder();
        try (InputStream inputStream = connection.getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }
}

