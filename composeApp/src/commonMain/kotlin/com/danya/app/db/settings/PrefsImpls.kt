package com.danya.app.db.settings

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import org.koin.core.component.KoinComponent

class PrefsImpl(val settings: Settings) : Prefs, KoinComponent {
    override var sessionToken: String
        get() = settings.getString(SessionTokenKey, "")
        set(value) = settings.set(SessionTokenKey, value)

    companion object {
        private const val SessionTokenKey = "SessionTokenKey"
    }
}