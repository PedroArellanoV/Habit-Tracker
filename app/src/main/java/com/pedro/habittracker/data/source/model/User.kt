package com.pedro.habittracker.data.source.model

data class User(
    val userId: String = "",
    val name: String,
    val email: String,
    val dailyGoal: Int = 0,
    val points: Int = 0
    )
