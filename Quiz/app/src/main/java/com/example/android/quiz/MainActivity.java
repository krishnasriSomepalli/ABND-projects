package com.example.android.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int noOfQuestions = 4;            // total number of questions in the quiz
    int thisQuestion = 0;                   // tracks the current question number
    int score = 0;
    // to identify the types of each question, with the question number
    int[] multiple = {2};
    int[] single = {1,4};
    int[] text = {3};
    // to identify the correct option(s)/answer depending on the question type
    int[][] multipleAns = {{1, 3, 4}};
    int[] singleAns = {3, 1};
    int[] textAns = {9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startQuiz(View view) throws ClassNotFoundException {
        // hides the introduction card
        findViewById(R.id.intro).setVisibility(View.GONE);
        nextQuestion();
    }
    private void nextQuestion() throws ClassNotFoundException {
        // hides previous question and displays current question
        int thisQuestionIdentifier = getResources().getIdentifier("question"+(thisQuestion+1),"id", "com.example.android.quiz");
        findViewById(thisQuestionIdentifier).setVisibility(View.VISIBLE);
        if(thisQuestion != 0){
            int prevQuestionIdentifier = getResources().getIdentifier("question"+(thisQuestion),"id", "com.example.android.quiz");
            findViewById(prevQuestionIdentifier).setVisibility(View.GONE);
        }
        thisQuestion++;
    }
    public void recordAnswer(View view) throws ClassNotFoundException {
        int index;
        boolean correct = true;
        // CheckBox(MCQ) type questions
        if((index = contains(multiple, thisQuestion)) != -1) {
            for(int i = 0; i < 4; i++) {
                int optionIdentifier = getResources().getIdentifier("q" + thisQuestion + "_o" + (i+1), "id", "com.example.android.quiz");
                CheckBox option = (CheckBox) findViewById(optionIdentifier);
                if(!((option.isChecked() && contains(multipleAns[index], i+1)>-1) || (!option.isChecked() && contains(multipleAns[index], i+1)==-1))) {
                    correct = false;
                    break;
                }
            }
            if(correct)
                score++;
            // resetting
            for(int i = 0; i < 4; i++) {
                int optionIdentifier = getResources().getIdentifier("q" + thisQuestion + "_o" + (i + 1), "id", "com.example.android.quiz");
                CheckBox option = (CheckBox) findViewById(optionIdentifier);
                if (option.isChecked()) {
                    option.setChecked(false);
                    option.jumpDrawablesToCurrentState();   // prevents animation when unchecking
                }
            }
        }
        // RadioButton type questions
        else if((index = contains(single, thisQuestion)) != -1) {
            int thisQuestionIdentifier = getResources().getIdentifier("options"+thisQuestion, "id", "com.example.android.quiz");
            RadioGroup options = findViewById(thisQuestionIdentifier);
            int selected = options.getCheckedRadioButtonId();
            RadioButton selectedOption = findViewById(selected);
            String selectedString = (String)selectedOption.getText();
            int answerIdentifier = getResources().getIdentifier("q"+thisQuestion+"_o"+singleAns[index], "string", "com.example.android.quiz");
            String answer = getResources().getString(answerIdentifier);
            if(answer.equals(selectedString))
                score++;
            // resetting
            selectedOption.setChecked(false);
            selectedOption.jumpDrawablesToCurrentState();
        }
        // text input type questions
        else if((index = contains(text, thisQuestion)) != -1) {
            int responseIdentifier = getResources().getIdentifier("response"+thisQuestion, "id","com.example.android.quiz");
            EditText response = findViewById(responseIdentifier);
            String responseText = response.getText().toString();
            if(responseText.equals(Integer.toString(textAns[index])))
                score++;
            // resetting
            response.getText().clear();
        }
        if(thisQuestion < noOfQuestions)
            nextQuestion();
        else
            endQuiz();
    }
    // checks if arr has the value in no and returns the index, if yes; else, -1 is returned
    private int contains(int[] arr, int no) {
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] == no)
                return i;
        }
        return -1;
    }
    // displays the score
    private void endQuiz() {
        int thisQuestionIdentifier = getResources().getIdentifier("question"+thisQuestion,"id", "com.example.android.quiz");
        findViewById(thisQuestionIdentifier).setVisibility(View.GONE);
        findViewById(R.id.end).setVisibility(View.VISIBLE);
        TextView end_msg = findViewById(R.id.end_msg);
        CharSequence msg = "You scored "+score+" out of "+noOfQuestions+"! Play again?";
        CharSequence text = "Your score: "+score+"/"+noOfQuestions+".";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(this, text, duration).show();
        end_msg.setText(msg);
    }
    // enables user to replay
    public void replayQuiz(View view) throws ClassNotFoundException {
        findViewById(R.id.end).setVisibility(View.GONE);
        findViewById(R.id.question1).setVisibility(View.VISIBLE);
        thisQuestion = 0;
        score = 0;
        nextQuestion();
    }
}
