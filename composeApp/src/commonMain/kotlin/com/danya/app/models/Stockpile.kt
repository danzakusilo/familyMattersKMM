package com.danya.app.models

import familyapp.composeapp.generated.resources.Res
import familyapp.composeapp.generated.resources.quant_type_amount
import familyapp.composeapp.generated.resources.quant_type_percentage
import familyapp.composeapp.generated.resources.quant_type_volume
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource

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
) : FirebaseAuthSensitiveItem

@Serializable
data class StockpileItemQuant(
    val unit: StockpileQuantType,
    val value: Float
)

@Serializable
sealed class StockpileQuantType(private val range: Pair<Float, Float>) {
    fun evalValueInRange(value: Float): Boolean {
        return value > range.first && value < range.second
    }

    abstract fun getNameRes(): StringResource
}

data object Volume : StockpileQuantType(Pair(0f, 10000f)) {
    override fun getNameRes(): StringResource {
        return Res.string.quant_type_volume
    }
}

data object Amount : StockpileQuantType(Pair(0f, 100f)) {
    override fun getNameRes(): StringResource {
        return Res.string.quant_type_amount
    }
}

data object Percentage : StockpileQuantType(Pair(0f, 100f)) {
    override fun getNameRes(): StringResource {
        return Res.string.quant_type_percentage
    }
}


interface FirebaseAuthSensitiveItem {
    val userId: String
    val familyId: String?
}
