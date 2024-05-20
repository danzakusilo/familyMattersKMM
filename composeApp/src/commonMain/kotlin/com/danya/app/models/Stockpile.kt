package com.danya.app.models

import kotlinx.serialization.Serializable

typealias Stockpile = Set<StockpileItemModel>

// Domain model to store in DB and FB
@Serializable
data class StockpileItemModel(
    val name: String,
    val quantType: String,
    val initialValue: Float,
    val category: String,
    val bottomLimitValue: Float,
    val price: String? = null,
    val imageUrl: String? = null,
    override val userId: String,
    override val familyId: String? = null
) : FirebaseAuthSensitiveItem

interface FirebaseAuthSensitiveItem {
    val userId: String
    val familyId: String?
}
