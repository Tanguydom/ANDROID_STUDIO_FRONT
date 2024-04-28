package com.example.projet_gerante_domergue.activity.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projet_gerante_domergue.activity.account.DeleteUserActivity;
import com.example.projet_gerante_domergue.activity.account.ModifyUserActivity;
import com.example.projet_gerante_domergue.models.User;


import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.activity.account.MyEventsActivity;
import com.example.projet_gerante_domergue.activity.account.MyParticipationsActivity;
import com.example.projet_gerante_domergue.utils.GetRequestTask;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

public class MyAccountActivity extends AppCompatActivity {
    private EditText editTextSurname, editTextName, editTextEmail, editTextMdp, editTextFunction;
    private int userId;
    private GetRequestTask getRequest;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //NAVIGATION
        Button buttonMesEvenements = findViewById(R.id.buttonMesEvenements);
        Button buttonMesParticipations = findViewById(R.id.buttonMesParticipations);

        //USER ID
        userId = getIntent().getIntExtra("id", 0);
        String idUserString = String.valueOf(userId);

        //USER INFOS
        try {
            getRequest = new GetRequestTask(); // Créer une instance de GetRequestTask
            String user = getRequest.execute("/utilisateurs/"+idUserString).get(); // Récupérer les informations de l'utilisateur
            Gson gson = new Gson(); // Créer un objet Gson
            User userInfos = gson.fromJson(user, User.class); // Convertir le JSON en objet User

            // Récupérer les vues des champs EditText
            TextView textViewNom = findViewById(R.id.textViewNom);
            TextView textViewPrenom = findViewById(R.id.textViewPrenom);
            TextView textViewEmail = findViewById(R.id.textViewEmail);
            TextView textViewFonction = findViewById(R.id.textViewFonction);
            TextView textViewLongitude = findViewById(R.id.textViewLongitude);
            TextView textViewLatitude = findViewById(R.id.textViewLatitude);

            // Mettre à jour le texte des champs EditText avec les informations de l'utilisateur
            textViewNom.setText("Nom : " + userInfos.getNom());
            textViewPrenom.setText("Prénom : " + userInfos.getPrenom());
            textViewEmail.setText("Email : " + userInfos.getEmail());
            textViewFonction.setText("Fonction : " + userInfos.getFonction());
            textViewLongitude.setText("Longitude : " + userInfos.getLongitude());
            textViewLatitude.setText("Latitude : " + userInfos.getLatitude());


        } catch (ExecutionException e) {
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Intent intent;
            int itemId = item.getItemId(); // Obtenez l'ID de l'élément de menu

            // Utilisez des constantes pour les identifiants des éléments de menu
            final int HOME_ID = R.id.navigation_evenements;
            final int CITY_ID = R.id.navigation_ville;

            if (itemId == HOME_ID) {
                intent = new Intent(MyAccountActivity.this, HomeActivity.class);
                intent.putExtra("id", userId);
                startActivity(intent);
                return true;
            } else if (itemId == CITY_ID) {
                intent = new Intent(MyAccountActivity.this, CityActivity.class);
                intent.putExtra("id", userId);
                startActivity(intent);
                return true;
            }

            return false;
        });

        // Bouton "Mes Événements Créés"
        buttonMesEvenements.setOnClickListener(v -> {
            Intent intent = new Intent(MyAccountActivity.this, MyEventsActivity.class);
            intent.putExtra("id", userId);
            startActivity(intent);
        });

        // Bouton "Mes Participations"
        buttonMesParticipations.setOnClickListener(v -> {
            Intent intent = new Intent(MyAccountActivity.this, MyParticipationsActivity.class);
            intent.putExtra("id", userId);
            startActivity(intent);
        });

        // Bouton "Modifier mon compte"
        Button buttonModifierMonCompte = findViewById(R.id.buttonModifier);
        buttonModifierMonCompte.setOnClickListener(v -> {
            Intent intent = new Intent(MyAccountActivity.this, ModifyUserActivity.class);
            intent.putExtra("id", userId);
            startActivity(intent);

        });

        // Bouton "Supprimer mon compte"
        Button buttonSupprimerMonCompte = findViewById(R.id.buttonSupprimer);
        buttonSupprimerMonCompte.setOnClickListener(v -> {
            Intent intent = new Intent(MyAccountActivity.this, DeleteUserActivity.class);
            intent.putExtra("id", userId);
            startActivity(intent);
        });
    }
}
