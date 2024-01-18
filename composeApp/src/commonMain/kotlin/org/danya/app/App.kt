package org.danya.app

import androidx.compose.runtime.Composable
import org.danya.app.theme.AppTheme
import org.danya.app.ui.login.LoginScreen

@Composable
internal fun App() = AppTheme {
    LoginScreen()
}

internal expect fun openUrl(url: String?)