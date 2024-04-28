package com.example.projet_gerante_domergue.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.models.Event;

public class EventViewHolder extends RecyclerView.ViewHolder {
    TextView eventNameTextView;
    TextView eventDescriptionTextView;
    TextView eventDateTextView;

    public EventViewHolder(View itemView) {
        super(itemView);
        eventNameTextView = itemView.findViewById(R.id.nom_evenement);
        eventDescriptionTextView = itemView.findViewById(R.id.description);
        eventDateTextView = itemView.findViewById(R.id.date_evenement);
    }

    public void bind(Event event) {
        eventNameTextView.setText(event.getNom_evenement());
        eventDescriptionTextView.setText(event.getDescription());
        eventDateTextView.setText(event.getDate_evenement());
    }
}
