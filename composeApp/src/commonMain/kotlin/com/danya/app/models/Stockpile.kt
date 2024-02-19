package com.danya.app.models

import kotlinx.serialization.Serializable

@Serializable
data class Stockpile(
    val items: List<StockpileItemModel>
)

@Serializable
data class StockpileItemModel(
    val name: String,
    val quant: String,
    val price: String,
    val imageUrl: String
)
