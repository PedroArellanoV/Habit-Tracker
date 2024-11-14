package com.pedro.habittracker.data.source.model

import com.google.firebase.Timestamp

data class Habit(
    val habitId : String = "",
    val name: String,
    val description: String = "",
    val icon: String = "",
    val color: String = "#FFFFFF",
    val streak: Int = 0,
    val targetTime: Timestamp? = null,
    val frequency: List<String> = listOf()
)