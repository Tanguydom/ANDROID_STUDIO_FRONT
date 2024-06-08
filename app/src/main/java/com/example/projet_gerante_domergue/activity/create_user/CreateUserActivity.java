package com.example.projet_gerante_domergue.activity.create_user;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.projet_gerante_domergue.MainActivity;
import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.activity.sqlite.MyDatabaseHelper;
import com.example.projet_gerante_domergue.utils.PostHttpRequestAsync;

public class CreateUserActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private EditText editTextNom;
    private EditText editTextPrenom;
    private EditText editTextEmail;
    private EditText editTextMotDePasse;
    private Spinner spinnerFonction;
    private Button buttonEnregistrer;
    private Button buttonRetour;
    private ImageView imageViewPhoto;
    private MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
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
        imageViewPhoto = findViewById(R.id.imageViewPhoto);

        // Vérifier et demander les permissions de la caméra
        checkCameraPermission();

        // Ajouter un écouteur de clic pour l'ImageView
        imageViewPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer l'intention pour capturer une photo
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

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

    private void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT).show();
            } else {
                // Permission denied
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageViewPhoto.setImageBitmap(imageBitmap);
        }
    }
}
