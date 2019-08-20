package com.theblackdiamonds.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.theblackdiamonds.R;

import java.util.Objects;

public class GameOverActivity extends AppCompatActivity {

    Button startGameAgain;
    TextView displayScore;
    String score;

    @SuppressLint({"SetTextI18n", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        startGameAgain = findViewById(R.id.play_again);
        displayScore = findViewById(R.id.score);

        score = Objects.requireNonNull(Objects.requireNonNull(getIntent().getExtras()).get("score")).toString();

        startGameAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameOverActivity.this, MainActivity.class));
            }
        });

        displayScore.setText("Score : " + score);
    }
}