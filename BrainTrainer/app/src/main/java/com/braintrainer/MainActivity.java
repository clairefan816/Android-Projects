package com.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    boolean gamePaused = false;



    // Method of generating the questions
    // randomly generate incorrect answers
    // put the correct answer and incorrect answers in the grid
    public void generateQuestion(){
        Random rand = new Random();
        //between 0 and 20
        int num1 = rand.nextInt(21);
        //between 0 and 20
        int num2 = rand.nextInt(21);

        sumTextView.setText(Integer.toString(num1) + " + " + Integer.toString(num2));

        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();

        int incorrectAnswer;

        for(int i = 0; i < 4; i++){
            if (i == locationOfCorrectAnswer){
                answers.add(num1 + num2);
            } else {
                incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer == num1 + num2){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }




//    public void start(View view){
//        startButton.setVisibility(View.INVISIBLE);
//    }


    public void chooseAnswer(View view){
        if (gamePaused) return;
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");
        } else {
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("10s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        gamePaused = false;

        generateQuestion();

        // attention to those little delay
        new CountDownTimer(10100, 1000) {

            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("Your Score is " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                playAgainButton.setVisibility(View.VISIBLE);
                gamePaused = true;
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);

        //attention here should pass in a View, any View
        playAgain(findViewById(R.id.playAgainButton));

    }

}