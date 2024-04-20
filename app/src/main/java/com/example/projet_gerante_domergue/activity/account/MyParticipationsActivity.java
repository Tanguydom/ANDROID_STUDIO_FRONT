package com.example.projet_gerante_domergue.activity.account;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.models.Participation;
import com.example.projet_gerante_domergue.utils.GetRequestTask;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class MyParticipationsActivity extends AppCompatActivity {

    private TextView textViewParticipationData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partcipation);

        textViewParticipationData = findViewById(R.id.textViewParticipation);

        try {
            GetRequestTask getRequest = new GetRequestTask();
            String participationData = getRequest.execute("/utilisateurs/1/evenementsParticipe").get();

            Gson gson = new Gson();
            Type participationType = new TypeToken<List<Participation>>(){}.getType();
            List<Participation> participations = gson.fromJson(participationData, participationType);

            StringBuilder participationsText = new StringBuilder();
            for (Participation participation : participations) {
                participationsText.append("nom_evenement: ").append(participation.getNom_evenement()).append("\n");
                participationsText.append("description: ").append(participation.getDescription()).append("\n");
                participationsText.append("date_evenement: ").append(participation.getDate_evenement()).append("\n");
                participationsText.append("nom_createur: ").append(participation.getNom_createur()).append("\n");
                participationsText.append("prenom_createur: ").append(participation.getPrenom_createur()).append("\n\n");
            }

            textViewParticipationData.setText(participationsText.toString());

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
