package my.edu.utar.individualassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button leaderboard;
    Button startgame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        startgame = findViewById(R.id.startgame);
        leaderboard = findViewById(R.id.leaderboard);

        startgame.setOnClickListener(view -> {
            Intent intent = new Intent(Menu.this,MainActivity.class);
            startActivity(intent);
        });

        leaderboard.setOnClickListener(view -> {
            Intent intent = new Intent(Menu.this,LEADERBOARD.class);
            startActivity(intent);
        });
    }
}