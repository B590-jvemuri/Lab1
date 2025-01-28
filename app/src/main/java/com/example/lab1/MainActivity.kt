package com.example.lab1

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lab1.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    //    lateinit- postpone the initialization of a variable until it is used, avoid null pointer exceptions
    private lateinit var binding: ActivityMainBinding
    private val questionBank = listOf(
        Question(R.string.question_Australia_text, true),
        Question(R.string.question_India_text, true),
        Question(R.string.question_USA_text, true),
        Question(R.string.question_UK_text, false),
        Question(R.string.question_UAE_text, false),
        Question(R.string.question_Water_text, false)
    )
    private var currentIndex = 0
    private var correctAnswers = 0
    private val answeredQuestions = mutableSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener { checkAnswer(true) }
        binding.falseButton.setOnClickListener { checkAnswer(false) }
        binding.previousButton.setOnClickListener {
            if (currentIndex == 0) {
                currentIndex = questionBank.size - 1
            } else {
                currentIndex = (currentIndex - 1) % questionBank.size
            }
            updateQuestion()
        }
        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        binding.questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
        updateButtonState()
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            correctAnswers++
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        answeredQuestions.add(currentIndex)
        updateButtonState()

        if (answeredQuestions.size == questionBank.size) {
            //delay the display of the score for 2 seconds so that the user can see the toast message
            Handler(Looper.getMainLooper()).postDelayed({
                displayScore()
            }, 2000)
        }
    }

    private fun updateButtonState() {
        val isQuestionAnswered = currentIndex in answeredQuestions
        binding.trueButton.isEnabled = !isQuestionAnswered
        binding.falseButton.isEnabled = !isQuestionAnswered
    }

    private fun displayScore() {
        Snackbar.make(
            binding.root,
            "Your score is $correctAnswers out of ${questionBank.size}",
            Snackbar.LENGTH_LONG
        ).show()
    }
}
