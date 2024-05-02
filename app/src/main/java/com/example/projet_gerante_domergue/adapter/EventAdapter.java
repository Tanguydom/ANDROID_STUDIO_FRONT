package com.example.projet_gerante_domergue.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.models.Event;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.models.Event;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventHolder>
{
    private List<Event> events;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onParticipateButtonClick(String eventId);
        void onDeleteParticipantButtonClick(String eventId);
        void onMapParticipationButtonClick(String adresse);
    }

    public EventAdapter(List<Event> events, OnItemClickListener listener) {
        this.events = events;
        this.listener = listener;
    }

    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position) {
        Event event = events.get(position);
        holder.bind(event);

        // Attribuer les tags aux boutons à partir de l'ID de l'événement
        holder.participateButton.setTag(event.getIdEvenement());
        holder.cancelParticipationButton.setTag(event.getIdEvenement());
        holder.mapParticipationButton.setTag(event.getAdresse());

        // Définir les listeners pour les boutons
        holder.participateButton.setOnClickListener(v -> {
            String eventId = v.getTag().toString();
            listener.onParticipateButtonClick(eventId);
        });

        holder.cancelParticipationButton.setOnClickListener(v -> {
            String eventId = v.getTag().toString();
            listener.onDeleteParticipantButtonClick(eventId);
        });

        holder.mapParticipationButton.setOnClickListener(v -> {
            String adresseEvent = v.getTag().toString();
            listener.onMapParticipationButtonClick(adresseEvent);
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
