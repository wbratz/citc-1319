package com.example.dicegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Scoreboard extends AppCompatActivity {

    private RecyclerView _recyclerView;
    private List<GameStats> _gameStatsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);

        TextView _userMessage = findViewById(R.id.messageToUser);
        TextView _totalDiceRolls = findViewById(R.id.totalDiceRolls);
        Button _returnToGame = findViewById(R.id.returnToGame);
        _gameStatsList = new ArrayList<>();

        _recyclerView = findViewById(R.id.scoreboardRecycler);
        SharedPreferences _sharedPreferences = getSharedPreferences("gameStats", MODE_PRIVATE);
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        _totalDiceRolls.setText(new StringBuilder()
                .append(intent.getIntExtra("diceRolls", 0))
                .append(" Total Dice Rolls:").toString());

        Map<String, ?> allEntries = _sharedPreferences.getAll();


        for (Map.Entry entry : allEntries.entrySet()) {
            Gson gson = new Gson();
            String json = entry.getValue().toString();
            GameStats gameStats = gson.fromJson(json, GameStats.class);
            _gameStatsList.add(gameStats);
        }

        String nameText = name.length() > 0 ? ", " + name : "";
        _userMessage.setText(_userMessage.getText() + nameText);
        setAdapter();

        _returnToGame.setOnClickListener(view -> finish());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_example, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                SettingsDialog settingsDialog = new SettingsDialog();
                settingsDialog.show(getSupportFragmentManager(), "");
                break;
            case R.id.about:
                AboutDialog aboutDialog = new AboutDialog();
                aboutDialog.show(getSupportFragmentManager(), "");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void setAdapter() {
        GameStatsAdapter _gameStatsAdapter = new GameStatsAdapter(_gameStatsList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        _recyclerView.setLayoutManager(layoutManager);
        _recyclerView.setItemAnimator(new DefaultItemAnimator());
        _recyclerView.setAdapter(_gameStatsAdapter);
    }
}
