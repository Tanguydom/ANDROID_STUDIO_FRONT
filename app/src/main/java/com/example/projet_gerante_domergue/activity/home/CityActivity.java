package com.example.projet_gerante_domergue.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.activity.create_city.CreateCityActivity;
import com.example.projet_gerante_domergue.adapter.CityAdapter;
import com.example.projet_gerante_domergue.adapter.ParticipationAdapter;
import com.example.projet_gerante_domergue.models.City;
import com.example.projet_gerante_domergue.utils.GetRequestTask;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class CityActivity extends AppCompatActivity {
    private int userId;
    private GetRequestTask getRequest = new GetRequestTask();

    private EditText editTextCityName, editTextCityCountry;
    private Button buttonCreateCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Récupérer l'ID de l'utilisateur
        userId = getIntent().getIntExtra("id", 0);
        String userIdString = String.valueOf(userId);
        buttonCreateCity = findViewById(R.id.buttonCreateCity);

        // recupération des villes
        try {
            String villesData = getRequest.execute("/villes/").get(); // Récupérer les villes

            Gson gson = new Gson(); // Créer un objet Gson
            Type cityType = new TypeToken<List<City>>(){}.getType(); // Créer un type pour la liste des villes
            List<City> cities = gson.fromJson(villesData, cityType); // Convertir les données JSON en liste de villes

            RecyclerView recyclerView = findViewById(R.id.recyclerViewCity); // Récupérer le RecyclerView
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new CityAdapter(cities)); // Afficher les villes dans le RecyclerView

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Ajouter un écouteur de clic pour le bouton "Créer une ville"
        buttonCreateCity.setOnClickListener(v -> {
            Intent intent = new Intent(CityActivity.this, CreateCityActivity.class);
            intent.putExtra("id", userId);
            startActivity(intent);
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Intent intent;
            int itemId = item.getItemId(); // Obtenez l'ID de l'élément de menu

            // Utilisez des constantes pour les identifiants des éléments de menu
            final int HOME_ID = R.id.navigation_evenements;
            final int PROFILE_ID = R.id.navigation_mon_compte;

            if (itemId == HOME_ID) {
                intent = new Intent(CityActivity.this, HomeActivity.class);
                intent.putExtra("id", userId);
                startActivity(intent);
                return true;
            } else if (itemId == PROFILE_ID) {
                intent = new Intent(CityActivity.this, MyAccountActivity.class);
                intent.putExtra("id", userId);
                startActivity(intent);
                return true;
            }

            return false;
        });

    }
}
