package com.danya.app.ui.login

import com.danya.app.api.login.LoginApi
import com.hoc081098.kmp.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val loginApi: LoginApi = LoginApi()) : ViewModel() {
    val name = MutableStateFlow("")

    fun register(email: String, password: String) {
        viewModelScope.launch {
            name.value = loginApi.registration(email, password)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            name.value = loginApi.login(email, password)
        }
    }
}
