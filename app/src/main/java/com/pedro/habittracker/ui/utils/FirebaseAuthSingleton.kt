package com.pedro.habittracker.ui.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object FirebaseAuthSingleton {
    val auth: FirebaseAuth by lazy { Firebase.auth }
}