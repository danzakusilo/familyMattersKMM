package org.danya.app.di

import io.github.aakira.napier.Napier
import org.koin.core.context.startKoin

fun initKoin() {
    Napier.d("Init koin ran")
    startKoin {
        modules(
            mainModule, ktorClientModule
        )
    }
}
