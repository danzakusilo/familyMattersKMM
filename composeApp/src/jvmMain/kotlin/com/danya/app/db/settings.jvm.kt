package com.danya.app.db

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import java.util.prefs.Preferences

actual fun createSettings(args: CreateSettingsArgs): Settings {
    val delegate: Preferences = Preferences.systemRoot()
    return PreferencesSettings(delegate)
}

actual class CreateSettingsArgs