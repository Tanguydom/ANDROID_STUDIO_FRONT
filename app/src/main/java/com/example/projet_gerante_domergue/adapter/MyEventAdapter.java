package com.example.projet_gerante_domergue.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_gerante_domergue.R;
import com.example.projet_gerante_domergue.models.MyEvent;

import java.util.List;

public class MyEventAdapter extends RecyclerView.Adapter<MyEventViewHolder> {
    private List<MyEvent> events;

    public MyEventAdapter(List<MyEvent> events) {
        this.events = events;
    }

    @Override
    public MyEventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myevent_item, parent, false);
        return new MyEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyEventViewHolder holder, int position) {
        MyEvent event = events.get(position);
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
