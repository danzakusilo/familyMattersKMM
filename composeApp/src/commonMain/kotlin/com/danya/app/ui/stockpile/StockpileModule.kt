package com.danya.app.ui.stockpile

import com.danya.app.ui.stockpile.create.CreateEditStockpileItemScreenModel
import org.koin.dsl.module

val stockpileModule = module {
    factory { StockpileScreenModel(get()) }
    factory { CreateEditStockpileItemScreenModel(get()) }
    single<StockpileApi> { StockpileApiIml() }
}