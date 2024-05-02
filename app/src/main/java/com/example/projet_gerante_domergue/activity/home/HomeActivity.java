package com.example.projet_gerante_domergue.activity.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.activity.create_event.CreateEventActivity;
import com.example.projet_gerante_domergue.activity.map.MapActivity;
import com.example.projet_gerante_domergue.adapter.EventAdapter;
import com.example.projet_gerante_domergue.models.Event;
import com.example.projet_gerante_domergue.utils.DeleteRequestTask;
import com.example.projet_gerante_domergue.utils.GetRequestTask;
import com.example.projet_gerante_domergue.utils.PostHttpRequestAsync;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private static final int NAVIGATION_VILLE = 1;
    private static final int NAVIGATION_MON_COMPTE = 2;
    private int userId;
    int itemId;
    private GetRequestTask getRequest = new GetRequestTask();
    private DeleteRequestTask deleteRequest = new DeleteRequestTask();
    private PostHttpRequestAsync postRequest = new PostHttpRequestAsync();
    private Button addEventButton, participateButton, deleteParticipantButton;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        addEventButton = findViewById(R.id.btnAjouterEvenement);
        recyclerView = findViewById(R.id.recyclerViewEvenements);

        userId = getIntent().getIntExtra("id", 0);

        addEventButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CreateEventActivity.class);
            intent.putExtra("id", userId);
            startActivity(intent);
        });

        try {
            String eventsData = getRequest.execute("/evenements/").get(); // Récupérer les événements

            Gson gson = new Gson(); // Créer un objet Gson
            Type eventType = new TypeToken<List<Event>>(){}.getType(); // Créer un type pour la liste des événements
            List<Event> events = gson.fromJson(eventsData, eventType); // Convertir les données JSON en liste d'événements

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            EventAdapter adapter = new EventAdapter(events, new EventAdapter.OnItemClickListener() {
                @Override
                public void onParticipateButtonClick(String eventId) {
                    try {
                        String postdata = "{\"Id_utilisateur\":" + userId + ",\"Id_evenement\":" + eventId + "}";
                        postRequest.execute("/participants/", postdata);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    intent.putExtra("id", userId);
                    startActivity(intent);
                }

                @Override
                public void onDeleteParticipantButtonClick(String eventId) {
                    try {
                        deleteRequest.execute("/participants/evenement/" + eventId + "/utilisateur/" + userId);
                        Toast.makeText(HomeActivity.this, "Vous avez annulé votre participation à l'événement", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(HomeActivity.this, "Erreur lors de l'annulation de la participation à l'événement", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    intent.putExtra("id", userId);
                    startActivity(intent);
                }

                @Override
                public void onMapParticipationButtonClick(String adresse) {
                    Intent intent = new Intent(HomeActivity.this, MapActivity.class);
                    intent.putExtra("id", userId);
                    intent.putExtra("adresse", adresse);
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(adapter);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Intent intent;
            itemId = item.getItemId(); // Obtenez l'ID de l'élément de menu
            userId = getIntent().getIntExtra("id", 0);

            // Utilisez des constantes pour les identifiants des éléments de menu
            final int CITY_ID = R.id.navigation_ville;
            final int PROFILE_ID = R.id.navigation_mon_compte;

            if (itemId == CITY_ID) {
                intent = new Intent(HomeActivity.this, CityActivity.class);
                intent.putExtra("id", userId);
                startActivity(intent);
                return true;
            } else if (itemId == PROFILE_ID) {
                intent = new Intent(HomeActivity.this, MyAccountActivity.class);
                intent.putExtra("id", userId);
                startActivity(intent);
                return true;
            }

            return false;
        });

    }
}
