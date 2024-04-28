    package com.example.projet_gerante_domergue;

    import android.content.Intent;
    import android.os.Bundle;
    import android.text.TextUtils;
    import android.util.Pair;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.appcompat.app.AppCompatActivity;
    import com.example.projet_gerante_domergue.activity.create_user.CreateUserActivity;
    import com.example.projet_gerante_domergue.activity.home.HomeActivity;
    import com.example.projet_gerante_domergue.utils.PostHttpRequestAsync;

    import org.json.JSONObject;

    import java.net.HttpURLConnection;


    public class MainActivity extends AppCompatActivity {

        private EditText editTextEmail;
        private EditText editTextPassword;
        private Button buttonLogin;
        private TextView textViewRegister;
        PostHttpRequestAsync postRequest = new PostHttpRequestAsync();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            // Associer les vues avec les éléments de la mise en page
            editTextEmail = findViewById(R.id.editTextPrenom);
            editTextPassword = findViewById(R.id.editTextPassword);
            buttonLogin = findViewById(R.id.buttonLogin);
            textViewRegister = findViewById(R.id.textViewRegister);


            // Ajouter un écouteur de clic pour le bouton de connexion
            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Récupérer les données saisies par l'utilisateur
                    String prenom = editTextEmail.getText().toString();
                    String password = editTextPassword.getText().toString();

                    // Vérifier si les champs sont vides
                    if (TextUtils.isEmpty(prenom)) {
                        editTextEmail.setError("Please enter your prenom");
                        return;
                    }

                    if (TextUtils.isEmpty(password)) {
                        editTextPassword.setError("Please enter your password");
                        return;
                    }

                    // Créer un objet JSON avec les données de connexion
                    String postData = String.format("{\"Prenom\":\"%s\",\"Mot_de_passe\":\"%s\"}", prenom, password);

                    // Envoyer les données à l'API
                    try {
                        // Exécuter la requête HTTP POST de manière asynchrone
                        postRequest = new PostHttpRequestAsync();
                        Pair<Integer, String> response = postRequest.execute("/connexion", postData).get();

                        int responseCode = response.first; // Récupérer le code de statut de la réponse
                        String responseJson = response.second; // Récupérer la réponse JSON

                        if (responseCode == HttpURLConnection.HTTP_OK) {

                            JSONObject jsonResponse = new JSONObject(responseJson);
                            int id = jsonResponse.getInt("Id_Utilisateur");

                            // Si la connexion réussit, afficher un message de succès et rediriger vers l'activité principale`
                            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            intent.putExtra("id", id);

                            startActivity(intent);
                        } else {
                            // Si la connexion échoue, afficher un message d'erreur
                            Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        // En cas d'erreur, afficher un message d'erreur
                        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });

            // Ajouter un écouteur de clic pour le lien "Register"
            textViewRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Rediriger l'utilisateur vers l'activité de création de compte
                    Intent intent = new Intent(MainActivity.this, CreateUserActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
