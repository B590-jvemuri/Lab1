package com.example.lab1

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import android.util.Log

private  const val  TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
class QuizViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_Australia_text, true),
        Question(R.string.question_India_text, true),
        Question(R.string.question_USA_text, true),
        Question(R.string.question_UK_text, false),
        Question(R.string.question_UAE_text, false),
        Question(R.string.question_Water_text, false)
    )
    private var currentIndex
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)
    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId
    fun moveToNext(){
//        Log.d(TAG, "Updating question text", Exception())
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