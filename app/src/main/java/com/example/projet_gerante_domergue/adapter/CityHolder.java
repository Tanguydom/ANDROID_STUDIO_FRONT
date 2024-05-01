package com.example.projet_gerante_domergue.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.models.City;

public class CityHolder extends RecyclerView.ViewHolder {

    TextView nom_ville;
    TextView nom_pays;

    public CityHolder(View itemView) {
        super(itemView);
        nom_ville = itemView.findViewById(R.id.textViewCityName);
        nom_pays = itemView.findViewById(R.id.textViewCountryName);
    }

    public void bind(City city) {
        nom_ville.setText(city.getName());
        nom_pays.setText(city.getCountry());
    }
}
