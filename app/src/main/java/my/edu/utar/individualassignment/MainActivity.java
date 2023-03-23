package my.edu.utar.individualassignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_main);

        // Initialize views
        View view1 = findViewById(R.id.view1);
        View view2 = findViewById(R.id.view2);
        View view3 = findViewById(R.id.view3);
        View view4 = findViewById(R.id.view4);
        views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        scoreTextView = findViewById(R.id.score);
        timerTextView = findViewById(R.id.timer);
        nextLevelButton = findViewById(R.id.nextLevelButton);

        // Initialize random number generator
        random = new Random();

        timerTextView = findViewById(R.id.timer);
        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> startTimer());

        nextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LVL2Activity.class);
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
                } }
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
