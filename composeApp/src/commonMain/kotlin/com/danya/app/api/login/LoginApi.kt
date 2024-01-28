package com.danya.app.api.login

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import org.koin.core.component.KoinComponent

class LoginApi : KoinComponent {
    private val auth = Firebase.auth

    suspend fun registration(email: String, password: String): String {
        val user = auth.createUserWithEmailAndPassword(email, password)
        return user.user?.getIdToken(false).toString()
    }

    suspend fun login(email: String, password: String): String {
        val user = auth.signInWithEmailAndPassword(email, password)
        return user.user?.getIdToken(false).toString()
    }
}
