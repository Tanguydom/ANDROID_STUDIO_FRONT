package com.example.projet_gerante_domergue.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_gerante_domergue.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private static final int NAVIGATION_VILLE = 1;
    private static final int NAVIGATION_MON_COMPTE = 2;
    private int userId;
    int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

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
