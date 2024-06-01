package com.danya.app.models

import kotlinx.serialization.Serializable

@Serializable
data class StockpilePostModel(
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