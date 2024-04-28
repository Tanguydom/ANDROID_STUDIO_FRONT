package com.example.projet_gerante_domergue.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.models.Participation;

import java.util.List;

public class ParticipationAdapter extends RecyclerView.Adapter<ParticipationHolder>
{
    private List<Participation> participations;

    public ParticipationAdapter(List<Participation> participations) {
        this.participations = participations;
    }

    @Override
    public ParticipationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.participation_item, parent, false);
        return new ParticipationHolder(view);
    }

    @Override
    public void onBindViewHolder(ParticipationHolder holder, int position) {
        Participation participation = participations.get(position);
        holder.bind(participation);
    }

    @Override
    public int getItemCount() {
        return participations.size();
    }

}
