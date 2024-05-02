package com.example.projet_gerante_domergue.activity.create_event;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_gerante_domergue.MainActivity;
import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.activity.home.HomeActivity;
import com.example.projet_gerante_domergue.models.City;
import com.example.projet_gerante_domergue.utils.GetRequestTask;
import com.example.projet_gerante_domergue.utils.PostHttpRequestAsync;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CreateEventActivity extends AppCompatActivity
{
    private int id_user;
    private Spinner spinnerCity;
    private Button createEventButton;
    private TextView textViewNomEvenement, textViewDescription, textViewDate, textViewAdresse;
    GetRequestTask getRequest = new GetRequestTask();
    PostHttpRequestAsync postRequest = new PostHttpRequestAsync();
    private List<City> cities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        id_user = getIntent().getIntExtra("id", 0);
        String id = String.valueOf(id_user);

        spinnerCity = findViewById(R.id.spinner_villes);
        createEventButton = findViewById(R.id.button_creer_evenement);

        textViewNomEvenement = findViewById(R.id.editText_nom_evenement);
        textViewDescription = findViewById(R.id.editText_description);
        textViewDate = findViewById(R.id.editText_date_evenement);
        textViewAdresse = findViewById(R.id.editText_adresse_evenement);

        try {
            String citiesData = getRequest.execute("/villes/").get(); // Récupérer les villes
            Gson gson = new Gson(); // Créer un objet Gson
            Type cityType = new TypeToken<List<City>>(){}.getType(); // Créer un type pour la liste des villes
            cities = gson.fromJson(citiesData, cityType); // Convertir les données JSON en liste de villes

            List<String> cityNames = new ArrayList<>();
            for (City city : cities) {
                cityNames.add(city.getName());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cityNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCity.setAdapter(adapter);
        }
        catch (Exception e) {
            e.printStackTrace();

        }

        createEventButton.setOnClickListener(v -> {
            String nomEvenement = textViewNomEvenement.getText().toString();
            String description = textViewDescription.getText().toString();
            String date = textViewDate.getText().toString();
            String adresse = textViewAdresse.getText().toString();

            String ville = spinnerCity.getSelectedItem().toString();
            int id_ville = 0;
            String idVille = "";

            City city = cities.stream()
                    .filter(c -> c.getName().equals(ville))
                    .findFirst()
                    .orElse(null);

            if (city != null) {
                id_ville = city.getId();
                idVille = String.valueOf(id_ville);
            }

            try {
                String postData = String.format("{\"Id_createur\":\"%s\",\"Id_ville\":\"%s\",\"nom_evenement\":\"%s\",\"Description\":\"%s\",\"Date_evenement\":\"%s\", \"Adresse\":\"%s\"}", id, idVille, nomEvenement, description, date, adresse);
                postRequest.execute("/evenements", postData);
                Toast.makeText(CreateEventActivity.this, "Evenement créé avec succès", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e) {
                Toast.makeText(CreateEventActivity.this, "Erreur lors de la création de l'événement", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            Intent intent = new Intent(CreateEventActivity.this, HomeActivity.class);
            intent.putExtra("id", id_user);
            startActivity(intent);
        });
    }
}
