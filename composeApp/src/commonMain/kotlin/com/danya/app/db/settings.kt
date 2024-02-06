package com.danya.app.db

import com.russhwolf.settings.Settings

expect fun createSettings(args: CreateSettingsArgs): Settings

expect class CreateSettingsArgs