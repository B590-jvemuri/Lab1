package com.example.lab1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.lab1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.trueButton.setOnClickListener {
            view: View ->
            Toast.makeText(
                this,
                R.string.correct_toast,
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.falseButton.setOnClickListener {
                view: View ->
            Toast.makeText(
                this,
                R.string.incorrect_toast,
                Toast.LENGTH_SHORT
            ).show()
        }
        val questionTextResId= questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }
}