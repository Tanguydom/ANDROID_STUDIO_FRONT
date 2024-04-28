package com.example.projet_gerante_domergue.activity.account;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.activity.home.MyAccountActivity;
import com.example.projet_gerante_domergue.utils.PostHttpRequestAsync;
import com.example.projet_gerante_domergue.utils.PutRequestTask;

import org.json.JSONObject;

import java.net.HttpURLConnection;

public class ModifyUserActivity extends AppCompatActivity implements LocationListener {
    private int userId;
    private Button buttonRetour;
    private EditText editTextNom;
    private EditText editTextPrenom;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Spinner editTextfunction;
    private Button buttonEnregistrer;
    private LocationManager locationManager;

    private double longitude;
    private double latitude;

    PutRequestTask putRequest = new PutRequestTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user);

        userId = getIntent().getIntExtra("id", 0);
        String idUserString = String.valueOf(userId);

        editTextNom = findViewById(R.id.editTextNomModifyUser);
        editTextPrenom = findViewById(R.id.editTextPrenomModifyUser);
        editTextEmail = findViewById(R.id.editTextEmailModifyUser);
        editTextPassword = findViewById(R.id.editTextMotDePasseModifyUser);
        editTextfunction = findViewById(R.id.spinnerFonctionModifyUser);

        buttonEnregistrer = findViewById(R.id.buttonModifierModifyUser);

        buttonRetour = findViewById(R.id.buttonRetourModifyUser);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Ajouter un écouteur de clic pour le bouton "Modifier"
        buttonEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = editTextNom.getText().toString();
                String prenom = editTextPrenom.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String fonction = editTextfunction.getSelectedItem().toString();

                // Récupérer les coordonnées de géolocalisation
                try {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ModifyUserActivity.this);
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location != null) {
                        longitude = location.getLongitude();
                        latitude = location.getLatitude();
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                }

                // Créer une chaîne JSON avec les données à envoyer, y compris les coordonnées de géolocalisation
                String postData = String.format("{\"Nom\":\"%s\",\"Prenom\":\"%s\",\"Email\":\"%s\",\"Mot_de_passe\":\"%s\",\"fonction\":\"%s\",\"longitude\":%f,\"latitude\":%f}", nom, prenom, email, password, fonction, longitude, latitude);


                // Envoyer les données à l'API
                try {
                    putRequest = new PutRequestTask();
                    String response = putRequest.execute("/utilisateurs/" + idUserString, postData).get();

                    if (response != null){
                        // Si la modification réussit, afficher un message de succès et rediriger vers l'activité "Mon compte"
                        Toast.makeText(ModifyUserActivity.this, "Utilisateur modifié avec succès", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(ModifyUserActivity.this, MyAccountActivity.class);
                        intent.putExtra("id", userId);

                        startActivity(intent);

                    }else {
                        // Afficher un message d'erreur
                        Toast.makeText(ModifyUserActivity.this, "Erreur lors de la modification de l'utilisateur", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } catch (Exception e) {
                    // Afficher un message d'erreur
                    Toast.makeText(ModifyUserActivity.this, "Erreur lors de la modification de l'utilisateur", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        // Ajouter un écouteur de clic pour le bouton "Retour"
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifyUserActivity.this, MyAccountActivity.class);
                intent.putExtra("id", userId);
                startActivity(intent);
            }
        });
    }

    // Méthode de l'interface LocationListener pour gérer les mises à jour de la localisation
    @Override
    public void onLocationChanged(Location location) {
        // Mettre à jour les coordonnées de géolocalisation si la localisation change
        if (location != null) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
    }

    // Méthodes de l'interface LocationListener qui doivent être implémentées mais peuvent être vides pour cet exemple
    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}
