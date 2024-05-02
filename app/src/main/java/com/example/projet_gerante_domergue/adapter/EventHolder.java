package com.example.projet_gerante_domergue.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.models.Event;

public class EventHolder extends RecyclerView.ViewHolder
{
    TextView nom_evenement;
    TextView description;
    TextView date_evenement;
    TextView adresse;
    TextView nom_createur;
    TextView prenom_createur;
    TextView email_createur;
    TextView fonction_createur;
    TextView ville_evenement;
    TextView pays_evenement;
    TextView participants;
    Button participateButton, cancelParticipationButton, mapParticipationButton;

    public EventHolder(View itemView) {
        super(itemView);
        nom_evenement = itemView.findViewById(R.id.text_nom_evenement);
        description = itemView.findViewById(R.id.text_description);
        date_evenement = itemView.findViewById(R.id.text_date_evenement);
        adresse = itemView.findViewById(R.id.text_adresse);
        nom_createur = itemView.findViewById(R.id.text_nom_createur);
        prenom_createur = itemView.findViewById(R.id.text_prenom_createur);
        email_createur = itemView.findViewById(R.id.text_email_createur);
        fonction_createur = itemView.findViewById(R.id.text_fonction_createur);
        ville_evenement = itemView.findViewById(R.id.text_ville);
        pays_evenement = itemView.findViewById(R.id.text_pays);
        participants = itemView.findViewById(R.id.text_participants);

        participateButton = itemView.findViewById(R.id.btnAjouterParticipant);
        cancelParticipationButton = itemView.findViewById(R.id.btnSupprimerParticipant);
        mapParticipationButton = itemView.findViewById(R.id.button_map_participation);
    }

    public void bind(Event event) {
        nom_evenement.setText(event.getNomEvenement());
        description.setText(event.getDescription());
        date_evenement.setText(event.getDateEvenement());
        adresse.setText(event.getAdresse());
        nom_createur.setText(event.getNomCreateurEvenement());
        prenom_createur.setText(event.getPrenomCreateurEvenement());
        email_createur.setText(event.getEmailCreateurEvenement());
        fonction_createur.setText(event.getFonctionCreateurEvenement());
        ville_evenement.setText(event.getVilleEvenement());
        pays_evenement.setText(event.getPaysEvenement());
        participants.setText(event.getParticipants());

        // Ajout de tags aux boutons à partir de l'ID de l'événement
        participateButton.setTag("participate_" + event.getIdEvenement());
        cancelParticipationButton.setTag("cancel_participation_" + event.getIdEvenement());
        mapParticipationButton.setTag("map_participation_" + event.getIdEvenement());
    }
}
