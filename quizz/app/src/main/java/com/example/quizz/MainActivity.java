package com.example.quizz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup answersGroup;
    private Button validateButton;
    private Button resetButton;
    private TextView feedbackText;
    private TextView questionText;
    private TextView questionArea;

    private int[] correctAnswers = {2, 3, 0};
    private int currentQuestionIndex = 0;

    private String[] areas = {
            "Futebol",
            "Jogos",
            "Tecnologia"
    };

    private String[] questions = {
            "Qual o jogador com mais gols na história?",
            "Qual o campeão de League of Legends conhecido pelo número 4?",
            "Qual a inteligência artificial mais usada?"
    };

    private String[][] options = {
            {"Pelé", "Lionel Messi", "Cristiano Ronaldo", "Zico", "Romário"},
            {"Zed", "Yasuo", "Ahri", "Jhin", "Lee Sin"},
            {"ChatGPT", "Siri", "Alexa", "Cortana", "Bixby"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionArea = findViewById(R.id.question_area);
        questionText = findViewById(R.id.question_text);
        answersGroup = findViewById(R.id.answers_group);
        validateButton = findViewById(R.id.validate_button);
        resetButton = findViewById(R.id.reset_button);
        feedbackText = findViewById(R.id.feedback_text);

        loadQuestion();

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetQuiz();
            }
        });
    }

    private void loadQuestion() {
        questionArea.setText(areas[currentQuestionIndex]);
        questionText.setText(questions[currentQuestionIndex]);
        RadioButton[] radioButtons = {
                findViewById(R.id.answer1),
                findViewById(R.id.answer2),
                findViewById(R.id.answer3),
                findViewById(R.id.answer4),
                findViewById(R.id.answer5)
        };
        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i].setText(options[currentQuestionIndex][i]);
        }
    }

    private void validateAnswer() {
        int selectedId = answersGroup.getCheckedRadioButtonId();
        RadioButton selectedAnswer = findViewById(selectedId);

        int selectedIndex = answersGroup.indexOfChild(selectedAnswer);
        if (selectedIndex == correctAnswers[currentQuestionIndex]) {
            feedbackText.setText("Correto!");
        } else {
            feedbackText.setText("Incorreto! A resposta correta é: " + options[currentQuestionIndex][correctAnswers[currentQuestionIndex]]);
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            loadQuestion();
        } else {
            feedbackText.append("\nQuizz concluído!");
            validateButton.setEnabled(false);
        }
    }

    private void resetQuiz() {
        currentQuestionIndex = 0;
        loadQuestion();
        feedbackText.setText("");
        validateButton.setEnabled(true);
    }
}
