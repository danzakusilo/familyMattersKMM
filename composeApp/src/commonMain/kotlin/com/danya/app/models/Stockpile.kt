package com.danya.app.models

import kotlinx.serialization.Serializable

@Serializable
data class Stockpile(
    val items: List<StockpileItemModel>
)

@Serializable
data class StockpileItemModel(
    val name: String,
    val quant: StockpileItemQuant,
    val price: String? = null,
    val imageUrl: String? = null,
    override val userId: String,
    override val familyId: String? = null
): FirebaseAuthSensitiveItem

@Serializable
data class StockpileItemQuant(
    val unit: StockpileQuantType,
    val value: Float
)

@Serializable
sealed class StockpileQuantType(private val range: Pair<Float, Float>) {
    fun evalValueInRange(value: Float): Boolean{
        return value > range.first && value < range.second
    }
}

data object Volume: StockpileQuantType(Pair(0f, 10000f))
data object Amount: StockpileQuantType(Pair(0f, 100f))
data object Percentage: StockpileQuantType(Pair(0f, 100f))



interface FirebaseAuthSensitiveItem {
    val userId: String
    val familyId: String?
}
