package com.danya.app.ui.stockpile

import org.koin.dsl.module

val stockpileModule = module {
    factory { StockpileScreenModel() }
}