package com.example.projet_gerante_domergue.activity.home;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;

import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.activity.account.MyEventsActivity;
import com.example.projet_gerante_domergue.activity.account.MyParticipationsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MyAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        Button buttonMesEvenements = findViewById(R.id.buttonMesEvenements);
        Button buttonMesParticipations = findViewById(R.id.buttonMesParticipations);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Intent intent;
            int itemId = item.getItemId(); // Obtenez l'ID de l'élément de menu

            // Utilisez des constantes pour les identifiants des éléments de menu
            final int HOME_ID = R.id.navigation_evenements;
            final int CITY_ID = R.id.navigation_ville;

            if (itemId == HOME_ID) {
                intent = new Intent(MyAccountActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == CITY_ID) {
                intent = new Intent(MyAccountActivity.this, CityActivity.class);
                startActivity(intent);
                return true;
            }

            return false;
        });

        // Bouton "Mes Événements Créés"
        buttonMesEvenements.setOnClickListener(v -> {
            Intent intent = new Intent(MyAccountActivity.this, MyEventsActivity.class);
            startActivity(intent);
        });

        // Bouton "Mes Participations"
        buttonMesParticipations.setOnClickListener(v -> {
            Intent intent = new Intent(MyAccountActivity.this, MyParticipationsActivity.class);
            startActivity(intent);
        });
    }
}
