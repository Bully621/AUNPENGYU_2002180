package my.edu.utar.individualassignment;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;
public class LVL5Activity extends AppCompatActivity {

    ArrayList<View> views;
    int score = 0;
    TextView scoreTextView;
    Random random;
    CountDownTimer countDownTimer;
    TextView timerTextView;
    Button startButton;
    Button nextLevelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvl5);
        View view1 = findViewById(R.id.view1);
        View view2 = findViewById(R.id.view2);
        View view3 = findViewById(R.id.view3);
        View view4 = findViewById(R.id.view4);
        View view5 = findViewById(R.id.view5);
        View view6 = findViewById(R.id.view6);
        View view7 = findViewById(R.id.view7);
        View view8 = findViewById(R.id.view8);
        View view9 = findViewById(R.id.view9);
        View view10 = findViewById(R.id.view10);
        View view11 = findViewById(R.id.view11);
        View view12 = findViewById(R.id.view12);
        View view13 = findViewById(R.id.view13);
        View view14 = findViewById(R.id.view14);
        View view15 = findViewById(R.id.view15);
        View view16 = findViewById(R.id.view16);
        View view17 = findViewById(R.id.view17);
        View view18 = findViewById(R.id.view18);
        View view19 = findViewById(R.id.view19);
        View view20 = findViewById(R.id.view20);
        View view21 = findViewById(R.id.view21);
        View view22 = findViewById(R.id.view22);
        View view23 = findViewById(R.id.view23);
        View view24 = findViewById(R.id.view24);
        View view25 = findViewById(R.id.view25);
        View view26 = findViewById(R.id.view26);
        View view27 = findViewById(R.id.view27);
        View view28 = findViewById(R.id.view28);
        View view29 = findViewById(R.id.view29);
        View view30 = findViewById(R.id.view30);
        View view31 = findViewById(R.id.view31);
        View view32 = findViewById(R.id.view32);
        View view33 = findViewById(R.id.view33);
        View view34 = findViewById(R.id.view34);
        View view35 = findViewById(R.id.view35);
        View view36 = findViewById(R.id.view36);
        views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        views.add(view5);
        views.add(view6);
        views.add(view7);
        views.add(view8);
        views.add(view9);
        views.add(view10);
        views.add(view11);
        views.add(view12);
        views.add(view13);
        views.add(view14);
        views.add(view15);
        views.add(view16);
        views.add(view17);
        views.add(view18);
        views.add(view19);
        views.add(view20);
        views.add(view21);
        views.add(view22);
        views.add(view23);
        views.add(view24);
        views.add(view25);
        views.add(view26);
        views.add(view27);
        views.add(view28);
        views.add(view29);
        views.add(view30);
        views.add(view31);
        views.add(view32);
        views.add(view33);
        views.add(view34);
        views.add(view35);
        views.add(view36);

        scoreTextView = findViewById(R.id.score);
        timerTextView = findViewById(R.id.timer);
        nextLevelButton = findViewById(R.id.nextLevelButton);
        score = this.getIntent().getIntExtra("score",score);

        // Initialize random number generator
        random = new Random();

        timerTextView = findViewById(R.id.timer);
        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> startTimer());
        nextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LVL5Activity.this, LEADERBOARD.class);
                intent.putExtra("score",score);
                startActivity(intent);
            }
        });
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Seconds Remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timerTextView.setText("Times Up!");
                for (View view : views) {
                    view.setOnTouchListener(null);
                }
            }
        };
        countDownTimer.start();

        // Highlight a random view
        highlightRandomView();
    }

    private void highlightRandomView() {
        // Find an unhighlighted view
        ArrayList<View> unhighlightedViews = new ArrayList<>();
        for (View view : views) {
            if (view.getTag() == null) {
                unhighlightedViews.add(view);
            }
        }

        // If there are no unhighlighted views, end the game
        if (unhighlightedViews.size() == 0) {
            countDownTimer.cancel();
            timerTextView.setText("Well Played!");
            return;
        }

        // Highlight a random unhighlighted view
        int randomIndex = random.nextInt(unhighlightedViews.size());
        View viewToHighlight = unhighlightedViews.get(randomIndex);
        viewToHighlight.setTag(true);
        viewToHighlight.setBackgroundColor(Color.rgb(130, 202, 255));
        //viewToHighlight.setAlpha(0.5f);
        viewToHighlight.setOnTouchListener((v, event) -> {
            if (v.getTag() != null) {
                // If the correct view was touched, increase score and highlight a new view
                score++;
                scoreTextView.setText("Score: " + score);
                v.setAlpha(1.0f);
                v.setOnTouchListener(null);
                highlightRandomView();
            }
            return true;
        });
    }
}