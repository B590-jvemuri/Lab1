package com.example.lab1

import android.util.Log
import androidx.lifecycle.ViewModel

private  const val  TAG = "QuizViewModel"
class QuizViewModel: ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_Australia_text, true),
        Question(R.string.question_India_text, true),
        Question(R.string.question_USA_text, true),
        Question(R.string.question_UK_text, false),
        Question(R.string.question_UAE_text, false),
        Question(R.string.question_Water_text, false)
    )
    private var currentIndex = 0
    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId
    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }
    fun moveToPrevious(){
        if (currentIndex == 0) {
            currentIndex = questionBank.size - 1
        }
        else{
            currentIndex = (currentIndex - 1) % questionBank.size
        }
    }
}