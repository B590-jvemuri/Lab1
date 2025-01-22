package com.example.lab1

import androidx.annotation.StringRes
//@StringRes annotation to ensure type safety for resource IDs, used to indicate that the textResId parameter represents a valid string resource ID (from res/values/strings.xml).
data class Question(@StringRes val textResId: Int, val answer: Boolean)