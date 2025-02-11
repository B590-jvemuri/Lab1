package com.example.lab1

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class QuizViewModelTest{
    @Test
    fun providesExpectedQuestionText(){
        val savedStateHandle = SavedStateHandle()
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_Australia_text, quizViewModel.currentQuestionText)
    }
    @Test
    fun wrapsAroundQuestionBank(){
        val savedStateHandle = SavedStateHandle(mapOf(CURRENT_INDEX_KEY to 5))
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_Water_text, quizViewModel.currentQuestionText)
        quizViewModel.moveToNext()
        assertEquals(R.string.question_Australia_text, quizViewModel.currentQuestionText)
    }
}