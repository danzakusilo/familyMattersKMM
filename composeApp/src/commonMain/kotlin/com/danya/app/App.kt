package com.danya.app

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.danya.app.theme.AppTheme
import com.danya.app.ui.login.LoginScreen

@Composable
internal fun App() = AppTheme {
    Navigator(LoginScreen())
}

internal expect fun openUrl(url: String?)