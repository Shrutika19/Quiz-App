package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView sumText;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int positionOfCorrectAns;
    TextView result;
    int score;
    int numOfQuiz;
    TextView scoreText;
    TextView timerText;
    Button playButton;
    ConstraintLayout gameLayout;

    public void start(View view){

        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View. VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    public void chooseAnswer(View view){

        if(Integer.toString(positionOfCorrectAns).equals(view.getTag().toString())){
            result.setText("Correct!");
            result.setVisibility(View.VISIBLE);
            score++;
        }else{
            result.setText("Incorrect!");
            result.setVisibility(View.VISIBLE);
        }

        numOfQuiz++;
        scoreText.setText(Integer.toString(score) + " / " +Integer.toString(numOfQuiz));
        newQuestion();

    }

    public void newQuestion(){

        Random rand = new Random();

        int num1 = rand.nextInt(21);
        int num2 = rand.nextInt(21);

        sumText.setText(Integer.toString(num1) + " + " + Integer.toString(num2));

        positionOfCorrectAns = rand.nextInt(4);

        answers.clear();

        for(int i= 0 ; i < 4; i++){

            if(i == positionOfCorrectAns){
                answers.add(num1 + num2);
            }else{
                int wrongAns = rand.nextInt(41);

                while(wrongAns == num1 + num2){
                    wrongAns = rand.nextInt(41);
                }
                answers.add(wrongAns);
            }
        }

        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get( 3)));
    }

    public void playAgain(View view){
            score = 0;
            numOfQuiz = 0;
            timerText.setText("30s");
            scoreText.setText(Integer.toString(score) + " / " +Integer.toString(numOfQuiz));

            newQuestion();
            playButton.setVisibility(View.INVISIBLE);

            new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timerText.setText(String.valueOf( l /1000) + "s");
            }

            @Override
            public void onFinish() {
                //result.setText("Time Up!");
                result.setVisibility(View.INVISIBLE);
                playButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        sumText = findViewById(R.id.sumTextView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        result = findViewById(R.id.resultText);
        scoreText = findViewById(R.id.scoreTextView);
        timerText = findViewById(R.id.timerTextView);
        playButton = findViewById(R.id.playButton);
        gameLayout = findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);

    }
}