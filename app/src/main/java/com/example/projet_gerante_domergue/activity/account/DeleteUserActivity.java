package com.example.projet_gerante_domergue.activity.account;

import static android.view.View.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_gerante_domergue.MainActivity;
import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.activity.home.MyAccountActivity;
import com.example.projet_gerante_domergue.utils.DeleteRequestTask;

public class DeleteUserActivity extends AppCompatActivity {
    private Button returnButton;
    private Button deleteUserButton;
    private int userId;
    private DeleteRequestTask deleteRequest = new DeleteRequestTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        userId = getIntent().getIntExtra("id", 0);
        String idUserString = String.valueOf(userId);

        returnButton = findViewById(R.id.buttonRetourDeleteUser);
        deleteUserButton = findViewById(R.id.textViewDeleteUser);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteUserActivity.this, MyAccountActivity.class);
                intent.putExtra("id", userId);
                startActivity(intent);
            }
        });

        deleteUserButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String response = deleteRequest.execute("/utilisateurs/" + idUserString).get();
                    if (response == null) {
                        Toast.makeText(DeleteUserActivity.this, "Erreur lors de la suppression de l'utilisateur", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(DeleteUserActivity.this, "Utilisateur supprimé", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(DeleteUserActivity.this, MainActivity.class);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
