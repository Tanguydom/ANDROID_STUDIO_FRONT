package com.example.projet_gerante_domergue.activity.account;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.adapter.MyEventAdapter;
import com.example.projet_gerante_domergue.models.MyEvent;
import com.example.projet_gerante_domergue.utils.GetRequestTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MyEventsActivity extends AppCompatActivity {

    private TextView textViewEventData;
    private int userId;
    private GetRequestTask getRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myevent);

        userId = getIntent().getIntExtra("id", 0);
        String userIdString = String.valueOf(userId);


        try {
            getRequest = new GetRequestTask();

            //String eventData = getRequest.execute("/utilisateurs/1/evenementsCree").get();
            String eventData = getRequest.execute("/utilisateurs/"+userIdString+"/evenementsCree").get();

            // Désérialiser le JSON en une liste d'objets Event
            Gson gson = new Gson();
            Type eventType = new TypeToken<List<MyEvent>>(){}.getType();
            List<MyEvent> events = gson.fromJson(eventData, eventType);

            RecyclerView recyclerView = findViewById(R.id.recyclerViewEvent);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new MyEventAdapter(events));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
