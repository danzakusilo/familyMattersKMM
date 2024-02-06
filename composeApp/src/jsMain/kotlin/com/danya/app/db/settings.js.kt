package com.danya.app.db

import com.russhwolf.settings.Settings
import com.russhwolf.settings.StorageSettings

actual fun createSettings(args: CreateSettingsArgs): Settings {
    return StorageSettings()
}

actual class CreateSettingsArgs