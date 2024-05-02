package com.example.projet_gerante_domergue.activity.create_city;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.activity.home.CityActivity;
import com.example.projet_gerante_domergue.utils.PostHttpRequestAsync;

import org.json.JSONObject;

public class CreateCityActivity extends AppCompatActivity {
    private EditText editTextCityName, editTextCityCountry;
    private Button buttonCreateCity, buttonRetour;
    private PostHttpRequestAsync postRequest = new PostHttpRequestAsync();
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_city);

        userId = getIntent().getIntExtra("id", 0);
        String userIdString = String.valueOf(userId);

        editTextCityName = findViewById(R.id.editTextCityName);
        editTextCityCountry = findViewById(R.id.editTextCityCountry);
        buttonCreateCity = findViewById(R.id.buttonCreateCity);
        buttonRetour = findViewById(R.id.buttonRetourCity);

        buttonCreateCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = editTextCityName.getText().toString();
                String cityCountry = editTextCityCountry.getText().toString();
                String postData = String.format("{\"Nom_ville\":\"%s\",\"Pays\":\"%s\"}", cityName, cityCountry);

                try {
                    postRequest.execute("/villes", postData);
                    Toast.makeText(CreateCityActivity.this, "Ville créée avec succès", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(CreateCityActivity.this, "Erreur lors de la création de la ville", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                Intent intent = new Intent(CreateCityActivity.this, CityActivity.class);
                intent.putExtra("id", userId);
                startActivity(intent);
            }
        });

        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateCityActivity.this, CityActivity.class);
                intent.putExtra("id", userId);
                startActivity(intent);
            }
        });
    }
}
