package com.danya.app.ui.login

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.danya.app.api.login.LoginApi
import com.danya.app.models.Stockpile
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.dsl.module

class LoginScreenModel(private val loginApi: LoginApi) : ScreenModel {
    val name = MutableStateFlow("")
    private val _authSuccessFull = MutableStateFlow(false)
    val authSuccessfull: StateFlow<Boolean>
        get() = _authSuccessFull

    fun register(email: String, password: String) {
        screenModelScope.launch {
            loginApi.registration(email, password).collectLatest {
                _authSuccessFull.value = it.isSuccess
            }
        }
    }

    fun login(email: String, password: String) {
        screenModelScope.launch {
            loginApi.login(email, password).collectLatest {
                _authSuccessFull.value = it.isSuccess
            }
        }
    }

    fun testPostToFb() {
        screenModelScope.launch {
            val store = Firebase.firestore
            store.collection("stockpile")
            store.collection("stockpile").add(
                Stockpile(values = mapOf("sour cream" to "None", "smetana" to "0.5"))
            )
        }
    }
}

val loginModule = module {
    factory { LoginScreenModel(get()) }
    single { LoginApi(get(), Firebase.auth) }
}
