package com.example.codewarrior928.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int numOfQuestions = 0;
    Questions q0 = new Questions("How do you say 'maybe' in Spanish?", "tal vez", 0);
    Questions q1 = new Questions("How do you say 'what's up' in Spanish?", "que Pasa", 1);
    Questions q2 = new Questions("How do you say 'next to' in Spanish?", "a lado de", 2);
    Questions q3 = new Questions("How do you say 'however' in Spanish?", "sin embargo", 3);
    Questions q4 = new Questions("How do you say 'each other' in Spanish?", "el uno del otro", 4);
//    Questions q5 = new Questions("How do you say 'of course' in Spanish?", "por supuesto", 5);
//    Questions q6 = new Questions("How do you say 'even though' in Spanish?", "a pesar de que", 6);

    private Questions[] questionArray = new Questions[5];

    private Questions currentQuestion;

    Random r = new Random();
    int low = 0;
    int high = questionArray.length;
    int randomNum = r.nextInt(high - low) + low;
    int correctAnswerPosition = r.nextInt((3) - low) + low;

    private int currentQIndex = 0;

    private void chooseNewAnswerPos() {
            correctAnswerPosition = r.nextInt((high-1) - low) + low;
    }

    private void chooseNewRandomQIndex() {
        randomNum = r.nextInt(high - low) + low;
        Log.i("randomNum", String.valueOf(randomNum));
    }

    private void chooseNewRandomQIndex(int invalidChoice) {
        while (randomNum == invalidChoice) {
            randomNum = r.nextInt(high - low) + low;
            Log.i("randomNum", String.valueOf(randomNum));
        }
    }

    private void chooseNewRandomQIndex(int invalidChoice0, int invalidChoice1) {
        while (randomNum == invalidChoice0 || randomNum == invalidChoice1) {
            randomNum = r.nextInt(high - low) + low;
            Log.i("randomNum", String.valueOf(randomNum));
        }
    }

    private void chooseNewRandomQIndex(int invalidChoice0, int invalidChoice1, int invalidChoice2) {
        while (randomNum == invalidChoice0 || randomNum == invalidChoice1 || randomNum == invalidChoice2) {
            randomNum = r.nextInt(high - low) + low;
            Log.i("randomNum", String.valueOf(randomNum));
        }
    }

    private void chooseNewRandomQIndex(int invalidChoice0, int invalidChoice1, int invalidChoice2, int invalidChoice3) {
        while (randomNum == invalidChoice0 || randomNum == invalidChoice1 || randomNum == invalidChoice2 || randomNum == invalidChoice3) {
            randomNum = r.nextInt(high - low) + low;
            Log.i("randomNum", String.valueOf(randomNum));
        }
    }

    private void initializeQuestionArray() {

        questionArray[0] = q0;
        questionArray[1] = q1;
        questionArray[2] = q2;
        questionArray[3] = q3;
        questionArray[4] = q4;
//        questionArray[5] = q5;

        currentQuestion = questionArray[currentQIndex];

        //        for (int i = 0; i < questionArray.length; i++) {
//            questionArray[i] = questionArray[i].getQuestionByIndex(i);
//        }
    }


    private void setQuestion(int newQuestionIndex) {
        currentQIndex = newQuestionIndex;
        currentQuestion = questionArray[newQuestionIndex];

        chooseNewAnswerPos();

        chooseNewRandomQIndex(newQuestionIndex);
        int numA = randomNum;
        chooseNewRandomQIndex(newQuestionIndex,numA);
        int numB = randomNum;
        chooseNewRandomQIndex(newQuestionIndex, numA, numB);
        int numC = randomNum;
        chooseNewRandomQIndex(newQuestionIndex, numA, numB, numC);
        int numD = randomNum;


        TextView questionText = (TextView) findViewById(R.id.currentQuestion);
        questionText.setText(currentQuestion.getQuestion());

        if(correctAnswerPosition == 0) {
            RadioButton optionC = (RadioButton) findViewById(R.id.optionA);
            optionC.setText(currentQuestion.getAnswer());
        } else {
            RadioButton optionA = (RadioButton) findViewById(R.id.optionA);
            optionA.setText(questionArray[numA].getAnswer());
        }

        if(correctAnswerPosition == 1) {
            RadioButton optionB = (RadioButton) findViewById(R.id.optionB);
            optionB.setText(currentQuestion.getAnswer());
        } else {
            RadioButton optionB = (RadioButton) findViewById(R.id.optionB);
            optionB.setText(questionArray[numB].getAnswer());
        }

        if(correctAnswerPosition == 2) {
            RadioButton optionC = (RadioButton) findViewById(R.id.optionC);
            optionC.setText(currentQuestion.getAnswer());
        } else {
            RadioButton optionC = (RadioButton) findViewById(R.id.optionC);
            optionC.setText(questionArray[numC].getAnswer());
        }

        if(correctAnswerPosition == 3) {
            RadioButton optionD = (RadioButton) findViewById(R.id.optionD);
            optionD.setText(currentQuestion.getAnswer());
        } else {
            RadioButton optionD = (RadioButton) findViewById(R.id.optionD);
            optionD.setText(questionArray[numD].getAnswer());
        }

        Log.i("numA, numB, numC, numD", String.valueOf(numA) + ", " + String.valueOf(numB) + ", " + String.valueOf(numC) + ", " + String.valueOf(numD));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeQuestionArray();
        setQuestion(currentQIndex);

    }


    public void submitAnswer(View view) {

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.answerSelection);
        int selectedAnswer = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = (RadioButton) findViewById(selectedAnswer);

        Log.i("Selected Answer", String.valueOf(selectedRadioButton.getText()));
        Log.i("CorrectAnsIndex", String.valueOf(correctAnswerPosition));
        Log.i("CorrectQuestionIndex", String.valueOf(currentQIndex));

        String selection = String.valueOf(selectedRadioButton.getText());

        if (selection.equals(currentQuestion.getAnswer())) {
            Toast.makeText(MainActivity.this, String.valueOf("Correct. Great job!!"), Toast.LENGTH_SHORT).show();
//            //sleep for 3 seconds
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            setQuestion(++currentQIndex%questionArray.length);
            Log.i("correctNumIndex", String.valueOf(correctAnswerPosition));

        } else {
            Toast.makeText(MainActivity.this, String.valueOf("Incorrect. Please try again!"), Toast.LENGTH_SHORT).show();
        }

    }

}

class Questions {
    private String question;
    private String answer;
    private int questionNum;

    public Questions() {
        this.question = null;
        this.answer = null;
        this.questionNum = 0;
    }

    public Questions(String mQuestion, String mAnswer, int mQuestionNum) {
        this.question = mQuestion;
        this.answer = mAnswer;
        this.questionNum = mQuestionNum;
    }

    public Questions getQuestionByIndex(int qIndex) {
        if (qIndex == questionNum)
            return this;
        else
            return null;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestion(String newQuestion) {
        this.question = newQuestion;
    }

    public void setAnswer(String newAnswer) {
        this.answer = newAnswer;
    }

}
