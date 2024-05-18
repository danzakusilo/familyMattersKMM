package com.danya.app.models

import familyapp.composeapp.generated.resources.Res
import familyapp.composeapp.generated.resources.amount_measurement
import familyapp.composeapp.generated.resources.percentage_measurement
import familyapp.composeapp.generated.resources.quant_type_amount
import familyapp.composeapp.generated.resources.quant_type_percentage
import familyapp.composeapp.generated.resources.quant_type_volume
import familyapp.composeapp.generated.resources.quant_type_weight
import familyapp.composeapp.generated.resources.volume_measurement
import familyapp.composeapp.generated.resources.weight_measurement
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource

typealias Stockpile = Set<StockpileItemModel>

@Serializable
data class StockpileItemModel(
    val name: String,
    val quantType: String,
    val initialValue: Float,
    val bottomLimitValue: Float,
    val price: String? = null,
    val imageUrl: String? = null,
    override val userId: String,
    override val familyId: String? = null
) : FirebaseAuthSensitiveItem

@Serializable
sealed class StockpileQuantType(private val range: Pair<Float, Float>, val name: String) {
    fun evalValueInRange(value: Float): Boolean {
        return value > range.first && value < range.second
    }

    abstract fun getMeasurementRes(): StringResource

    abstract fun getNameRes(): StringResource
}

@Serializable
data object Volume : StockpileQuantType(Pair(0f, 10000f), "Volume") {
    override fun getMeasurementRes(): StringResource {
        return Res.string.volume_measurement
    }

    override fun getNameRes(): StringResource {
        return Res.string.quant_type_volume
    }
}

@Serializable
data object Amount : StockpileQuantType(Pair(0f, 100f), "Amount") {
    override fun getMeasurementRes(): StringResource {
        return Res.string.amount_measurement
    }

    override fun getNameRes(): StringResource {
        return Res.string.quant_type_amount
    }
}

@Serializable
data object Percentage : StockpileQuantType(Pair(0f, 100f), "Percentage") {
    override fun getMeasurementRes(): StringResource {
        return Res.string.percentage_measurement
    }

    override fun getNameRes(): StringResource {
        return Res.string.quant_type_percentage
    }
}

@Serializable
data object Weight : StockpileQuantType(Pair(0f, 99999f), "Weight") {
    override fun getMeasurementRes(): StringResource {
        return Res.string.weight_measurement
    }

    override fun getNameRes(): StringResource {
        return Res.string.quant_type_weight
    }
}


interface FirebaseAuthSensitiveItem {
    val userId: String
    val familyId: String?
}
