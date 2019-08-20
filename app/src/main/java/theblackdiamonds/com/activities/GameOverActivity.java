package theblackdiamonds.com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import theblackdiamonds.com.R;

public class GameOverActivity extends AppCompatActivity {

    Button startGameAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        startGameAgain = findViewById(R.id.play_again);
        startGameAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameOverActivity.this, MainActivity.class));
            }
        });
    }
}