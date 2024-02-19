package com.danya.app.ui.stockpile.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateStockPileItemUIModel(
    val name: String,
    val quant: String
)
