package com.example.myapplication.ui.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentQuizBinding;
import com.example.myapplication.ui.quiz.QuizViewModel;

import java.text.NumberFormat;

public class QuizFragment extends Fragment {

    private static final NumberFormat intFormat = NumberFormat.getNumberInstance();   // float formatter objects
    private FragmentQuizBinding binding;
    private TextView questionText; // shows calculated bmi
    private Button nextQuestionButton;
    private int currentQuestion;
    private int score;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        QuizViewModel kcalViewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        binding = FragmentQuizBinding.inflate(inflater, container, false);
        questionText = binding.questionContent;
        nextQuestionButton = binding.nextQuestion;
        setNextQuestion();
        nextQuestionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(currentQuestion == 6)
                {
                    resetQuiz();
                    setNextQuestion();
                }
                else
                {
                    checkAnswer();
                    currentQuestion++;
                    setNextQuestion();
                }
            }
        });

        View root = binding.getRoot();
        return root;
    }

    public void checkAnswer()
    {
        boolean trueValue = ((RadioButton)binding.trueValue).isChecked();
        boolean falseValue = ((RadioButton)binding.falseValue).isChecked();

        int textId = 0;
        boolean answer = false;

        if(currentQuestion > 5)
            return;

            switch (currentQuestion)
            {
                case 0:
                    textId = R.string.answer1;
                    break;
                case 1:
                    textId = R.string.answer2;
                    break;
                case 2:
                    textId = R.string.answer3;
                    break;
                case 3:
                    textId = R.string.answer4;
                    break;
                case 4:
                    textId = R.string.answer5;
                    break;
                case 5:
                    textId = R.string.answer6;
                    break;
            }
            String answerValue = getResources().getString(textId);
            answer = Boolean.parseBoolean(answerValue);


            if(answer && trueValue)
            {
                score++;
            }
            else if(!answer && falseValue)
            {
                score++;
            }

    }

    public void setNextQuestion()
    {
        int textId = 0;
        String targetText = "";

        switch (currentQuestion)
        {
            case 0:
                textId = R.string.question1;
                break;
            case 1:
                textId = R.string.question2;
                break;
            case 2:
                textId = R.string.question3;
                break;
            case 3:
                textId = R.string.question4;
                break;
            case 4:
                textId = R.string.question5;
                break;
            case 5:
                textId = R.string.question6;
                break;
            case 6:
                textId = R.string.finalScore;
                break;
        }
        targetText = getResources().getString(textId);
        if(currentQuestion == 6)
        {
            targetText += " "+score;
            nextQuestionButton.setText("Try Again");
        }


        questionText.setText(targetText);
    }

    public void resetQuiz()
    {
        score = 0;
        currentQuestion = 0;
        nextQuestionButton.setText("Confirm");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}