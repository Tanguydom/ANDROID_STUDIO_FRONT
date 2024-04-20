package com.example.projet_gerante_domergue.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projet_gerante_domergue.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Intent intent;
            int itemId = item.getItemId(); // Obtenez l'ID de l'élément de menu

            // Utilisez des constantes pour les identifiants des éléments de menu
            final int HOME_ID = R.id.navigation_evenements;
            final int PROFILE_ID = R.id.navigation_mon_compte;

            if (itemId == HOME_ID) {
                intent = new Intent(CityActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == PROFILE_ID) {
                intent = new Intent(CityActivity.this, MyAccountActivity.class);
                startActivity(intent);
                return true;
            }

            return false;
        });

    }
}
