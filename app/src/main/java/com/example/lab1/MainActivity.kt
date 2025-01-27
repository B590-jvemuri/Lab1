package com.example.lab1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lab1.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }
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
    override fun onStart(){
        super.onStart()
        Log.d(TAG,"onStart() called")
    }
    override fun onResume(){
        super.onResume()
        Log.d(TAG,"onResume() called")
    }
    override fun onPause(){
        super.onPause()
        Log.d(TAG,"onPause() called")
    }
    override fun onStop(){
        super.onStop()
        Log.d(TAG,"onStop() called")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG,"onDestroy() called")
    }
    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer= questionBank[currentIndex].answer
        val messageResId= if(userAnswer==correctAnswer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show()
    }
}

//9 slide(home button) - onPause() and onStop()
//10 slide(recents and reopen)- onStart() and onResume()
//11 slide(swipe off from recent)- onDestroy()
//12 slide - onPause(), onStop(), onDestroy(), onCreate(Bundle?), onStart(), onResume()