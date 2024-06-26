package com.danya.app.ui.stockpile

import com.danya.app.ui.stockpile.list.StockpileItemCategory
import com.danya.app.ui.stockpile.list.StockpileQuantType
import kotlinx.serialization.Serializable

@Serializable
data class StockpileInputModel(
    val name: String,
    val quantType: StockpileQuantType,
    val value: String,
    val bottomLimitValue: String,
    val selectedCategory: StockpileItemCategory
)
