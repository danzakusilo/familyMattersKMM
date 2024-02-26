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
    val price: String? = null,
    val imageUrl: String? = null,
    override val userId: String
): FirebaseAuthSensitiveItem

interface FirebaseAuthSensitiveItem {
    val userId: String
}
