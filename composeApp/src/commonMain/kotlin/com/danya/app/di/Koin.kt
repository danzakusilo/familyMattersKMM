package com.danya.app.di

import com.danya.app.db.settings.prefsModule
import com.danya.app.db.settings.settingsModule
import io.github.aakira.napier.Napier
import org.koin.core.context.startKoin

fun initKoin() {
    Napier.d("Init koin ran")
    startKoin {
        modules(
            mainModule, ktorClientModule, settingsModule, prefsModule
        )
    }
}
