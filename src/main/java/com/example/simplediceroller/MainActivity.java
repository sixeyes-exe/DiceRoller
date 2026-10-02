package com.example.simplediceroller;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButtonListenerMethod();
    }

    public void myButtonListenerMethod() {
        Button button = (Button) findViewById(R.id.rollButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView img = (ImageView) findViewById(R.id.diceImage);
                TextView diceResult = (TextView) findViewById(R.id.diceResult);

                // 1. Define the rotation animation (spins 2 full turns / 720 degrees)
                RotateAnimation rotate = new RotateAnimation(
                        0, 720,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
                );
                rotate.setDuration(500); // Animation runs for 500 milliseconds (0.5 seconds)

                // 2. Add an AnimationListener to trigger the roll outcome when spinning finishes
                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        diceResult.setText("Rolling...");
                        diceResult.setTextColor(Color.GRAY);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // Generate random number after animation ends
                        Random rand = new Random();
                        int rollResult = rand.nextInt(6) + 1;

                        diceResult.setText(Integer.toString(rollResult));

                        switch (rollResult) {
                            case 1:
                                img.setImageResource(R.drawable.dice1);
                                diceResult.setTextColor(Color.parseColor("#E53935")); // Red
                                break;
                            case 2:
                                img.setImageResource(R.drawable.dice2);
                                diceResult.setTextColor(Color.parseColor("#FB8C00")); // Orange
                                break;
                            case 3:
                                img.setImageResource(R.drawable.dice3);
                                diceResult.setTextColor(Color.parseColor("#FDD835")); // Yellow
                                break;
                            case 4:
                                img.setImageResource(R.drawable.dice4);
                                diceResult.setTextColor(Color.parseColor("#4CAF50")); // Green
                                break;
                            case 5:
                                img.setImageResource(R.drawable.dice5);
                                diceResult.setTextColor(Color.parseColor("#1E88E5")); // Blue
                                break;
                            case 6:
                                img.setImageResource(R.drawable.dice6);
                                diceResult.setTextColor(Color.parseColor("#8E24AA")); // Purple
                                break;
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });

                // 3. Start the animation on the ImageView
                img.startAnimation(rotate);
            }
        });
    }
}