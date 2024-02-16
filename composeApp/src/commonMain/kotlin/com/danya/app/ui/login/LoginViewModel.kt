package com.danya.app.ui.login

import com.danya.app.api.login.LoginApi
import com.danya.app.models.Stockpile
import com.hoc081098.kmp.viewmodel.ViewModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginViewModel(private val loginApi: LoginApi = LoginApi()) : ViewModel() {
    val name = MutableStateFlow("")
    private val _authSuccessFull = MutableStateFlow(false)
    val authSuccessfull: StateFlow<Boolean>
        get() = _authSuccessFull

    fun register(email: String, password: String) {
        viewModelScope.launch {
            loginApi.registration(email, password).collectLatest {
                _authSuccessFull.value = it.isSuccess
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginApi.login(email, password).collectLatest {
                _authSuccessFull.value = it.isSuccess
            }
        }
    }

    fun testPostToFb() {
        viewModelScope.launch {
            val store = Firebase.firestore
            store.collection("stockpile")
            store.collection("stockpile").add(
                Stockpile(values = mapOf("sour cream" to "None", "smetana" to "0.5"))
            )
        }
    }
}
