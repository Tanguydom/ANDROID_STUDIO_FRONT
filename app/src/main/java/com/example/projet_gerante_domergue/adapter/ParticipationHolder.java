package com.example.projet_gerante_domergue.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.models.Participation;

public class ParticipationHolder extends RecyclerView.ViewHolder {

    TextView nom_participation;
    TextView description_participation;
    TextView date_participation;
    TextView nom_createur;
    TextView prenom_createur;


    public ParticipationHolder(View itemView) {
        super(itemView);
        nom_participation = itemView.findViewById(R.id.nom_participation);
        description_participation = itemView.findViewById(R.id.description_participation);
        date_participation = itemView.findViewById(R.id.date_participation);
        nom_createur = itemView.findViewById(R.id.nom_createur);
        prenom_createur = itemView.findViewById(R.id.prenom_createur);

    }

    public void bind(Participation participation) {
        nom_participation.setText(participation.getNom_evenement());
        description_participation.setText(participation.getDescription());
        date_participation.setText(participation.getDate_evenement());
        nom_createur.setText(participation.getNom_createur());
        prenom_createur.setText(participation.getPrenom_createur());
    }


}
