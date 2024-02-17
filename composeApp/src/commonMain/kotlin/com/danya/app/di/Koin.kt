package com.danya.app.di

import com.danya.app.db.settings.prefsModule
import com.danya.app.db.settings.settingsModule
import com.danya.app.ui.home.homeModule
import com.danya.app.ui.login.loginModule
import com.danya.app.ui.stockpile.stockpileModule
import io.github.aakira.napier.Napier
import org.koin.core.context.startKoin

fun initKoin() {
    Napier.d("Init koin ran")
    startKoin {
        modules(
            mainModule,
            ktorClientModule,
            settingsModule,
            prefsModule,
            loginModule,
            homeModule,
            stockpileModule
        )
    }
}
