package com.example.projet_gerante_domergue.activity.login;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.projet_gerante_domergue.MainActivity;
import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.utils.PostHttpRequestAsync;

public class CreateUserActivity extends AppCompatActivity {

    private EditText editTextNom;
    private EditText editTextPrenom;
    private EditText editTextEmail;
    private EditText editTextMotDePasse;
    private Spinner spinnerFonction;
    private Button buttonEnregistrer;
    private Button buttonRetour;
    PostHttpRequestAsync postRequest = new PostHttpRequestAsync();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        // Associer les vues avec les éléments de la mise en page
        editTextNom = findViewById(R.id.editTextNom);
        editTextPrenom = findViewById(R.id.editTextPrenom);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMotDePasse = findViewById(R.id.editTextMotDePasse);
        spinnerFonction = findViewById(R.id.spinnerFonction);
        buttonEnregistrer = findViewById(R.id.buttonEnregistrer);
        buttonRetour = findViewById(R.id.buttonRetour);

        // Ajouter un écouteur de clic pour le bouton "Enregistrer"
        buttonEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les valeurs saisies dans les champs
                String nom = editTextNom.getText().toString();
                String prenom = editTextPrenom.getText().toString();
                String email = editTextEmail.getText().toString();
                String motDePasse = editTextMotDePasse.getText().toString();
                String fonction = spinnerFonction.getSelectedItem().toString();

                try {
                    // Créer une chaîne JSON avec les données à envoyer
                    String postData = String.format("{\"Nom\":\"%s\",\"Prenom\":\"%s\",\"Email\":\"%s\",\"Mot_de_passe\":\"%s\",\"fonction\":\"%s\"}", nom, prenom, email, motDePasse, fonction);

                    // Envoyer les données à l'API
                    try {
                        postRequest.execute("/utilisateurs", postData);
                        // Afficher un message de succès
                        Toast.makeText(CreateUserActivity.this, "Utilisateur créé avec succès", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                        // Afficher un message d'erreur
                        Toast.makeText(CreateUserActivity.this, "Erreur lors de la création de l'utilisateur", Toast.LENGTH_SHORT).show();
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Retourner à l'activité principale (MainActivity)
                Intent intent = new Intent(CreateUserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Ajouter un écouteur de clic pour le bouton "Retour"
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retourner à l'activité principale (MainActivity)
                Intent intent = new Intent(CreateUserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
