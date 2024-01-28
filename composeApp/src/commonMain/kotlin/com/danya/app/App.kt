package com.danya.app

import androidx.compose.runtime.Composable
import com.danya.app.theme.AppTheme
import com.danya.app.ui.login.LoginScreen

@Composable
internal fun App() = AppTheme {
    LoginScreen()
}

internal expect fun openUrl(url: String?)