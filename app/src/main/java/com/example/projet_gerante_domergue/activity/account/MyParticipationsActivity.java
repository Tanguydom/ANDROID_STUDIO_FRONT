package com.example.projet_gerante_domergue.activity.account;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.adapter.ParticipationAdapter;
import com.example.projet_gerante_domergue.models.Participation;
import com.example.projet_gerante_domergue.utils.GetRequestTask;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class MyParticipationsActivity extends AppCompatActivity {

    private TextView textViewParticipationData;
    private int userId;
    GetRequestTask getRequest = new GetRequestTask();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypartcipation);

        userId = getIntent().getIntExtra("id", 0);
        String userIdString = String.valueOf(userId);

        textViewParticipationData = findViewById(R.id.textViewParticipation);

        try {
            //String participationData = getRequest.execute("/utilisateurs/1/evenementsParticipe").get();
            String participationData = getRequest.execute("/utilisateurs/"+userIdString+"/evenementsParticipe").get();

            Gson gson = new Gson();
            Type participationType = new TypeToken<List<Participation>>(){}.getType();
            List<Participation> participations = gson.fromJson(participationData, participationType);

            RecyclerView recyclerView = findViewById(R.id.recyclerViewParticipation);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new ParticipationAdapter(participations));


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
