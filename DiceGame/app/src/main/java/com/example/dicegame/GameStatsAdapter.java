package com.example.dicegame;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GameStatsAdapter extends RecyclerView.Adapter<GameStatsAdapter.ListItemHolder> {

    private final List<GameStats> gameStatsList;
    private final int _color1 = Color.parseColor("#334B48");
    private final int _color2 = Color.parseColor("#96B1AD");
    private String currentColor = "color1";

    public GameStatsAdapter(List<GameStats> gameStatsList) {
        this.gameStatsList = gameStatsList;
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem, parent, false);

        return new ListItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameStatsAdapter.ListItemHolder holder, int position) {
        GameStats gameStats = gameStatsList.get(position);
        holder.score.setText(String.valueOf(gameStats.getTotalScore()));
        holder.doubles_triples.setText("Doubles: " + gameStats.getTotalDoubles() + " - Triples: " + gameStats.getTotalTriples());
        holder.userName.setText(gameStats.getUserName());
        holder.itemView.setBackgroundColor(getColor());
    }

    private int getColor() {
        if (currentColor.equals("color1")) {
            currentColor = "color2";

            return _color2;
        }

        currentColor = "color1";
        return _color1;
    }

    @Override
    public int getItemCount() {
        return gameStatsList.size();
    }

    public static class ListItemHolder extends RecyclerView.ViewHolder {

        private final TextView score;
        private final TextView userName;
        private final TextView doubles_triples;

        public ListItemHolder(@NonNull View itemView) {
            super(itemView);

            score = itemView.findViewById(R.id.score);
            userName = itemView.findViewById(R.id.userName);
            doubles_triples = itemView.findViewById(R.id.doubles_triples);
        }
    }
}
