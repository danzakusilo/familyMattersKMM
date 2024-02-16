package com.danya.app.ui.login

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.danya.app.api.login.LoginApi
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
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

    init {
        screenModelScope.launch {
            loginApi.checkUserLoggedIn().collectLatest {
                _authSuccessFull.value = it.isSuccess && it.getOrNull() == true
            }
        }
    }

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
}

val loginModule = module {
    factory { LoginScreenModel(get()) }
    single { LoginApi(Firebase.auth) }
}
