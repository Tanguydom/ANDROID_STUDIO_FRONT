package com.example.projet_gerante_domergue.activity.account;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.models.Event;
import com.example.projet_gerante_domergue.utils.GetRequestTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MyEventsActivity extends AppCompatActivity {

    private TextView textViewEventData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myevent);

        textViewEventData = findViewById(R.id.textViewEventData);

        try {
            GetRequestTask getRequest = new GetRequestTask();
            String eventData = getRequest.execute("/utilisateurs/1/evenementsCree").get();

            // Désérialiser le JSON en une liste d'objets Event
            Gson gson = new Gson();
            Type eventType = new TypeToken<List<Event>>(){}.getType();
            List<Event> events = gson.fromJson(eventData, eventType);

            // Construction de la chaîne de texte pour afficher les événements
            StringBuilder eventsText = new StringBuilder();
            for (Event event : events) {
                eventsText.append("Nom: ").append(event.getNom_evenement()).append("\n");
                eventsText.append("Description: ").append(event.getDescription()).append("\n");
                eventsText.append("Date: ").append(event.getDate_evenement()).append("\n\n");
            }

            // Définir le texte formaté dans le TextView
            textViewEventData.setText(eventsText.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
