package com.danya.app.db.settings

import com.russhwolf.settings.Settings
import org.koin.dsl.module

internal val settingsModule = module {
    single {
        Settings()
    }
}

internal val prefsModule = module {
    single<Prefs> {
        PrefsImpl(get())
    }
}