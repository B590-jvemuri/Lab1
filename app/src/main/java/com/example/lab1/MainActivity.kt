package com.example.lab1

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.lab1.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    //    lateinit- postpone the initialization of a variable until it is used, avoid null pointer exceptions
    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()
    private var correctAnswers = 0
    private val answeredQuestions = mutableSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")
        binding.trueButton.setOnClickListener { checkAnswer(true) }
        binding.falseButton.setOnClickListener { checkAnswer(false) }
        binding.previousButton.setOnClickListener {
            quizViewModel.moveToPrevious()
            updateQuestion()
        }
        binding.nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }
        binding.questionTextView.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = if (userAnswer == correctAnswer) {
            correctAnswers++
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}
