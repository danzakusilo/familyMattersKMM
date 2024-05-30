package com.danya.app.ui.stockpile.list

import familyapp.composeapp.generated.resources.Res
import familyapp.composeapp.generated.resources.amount_measurement
import familyapp.composeapp.generated.resources.category_candy
import familyapp.composeapp.generated.resources.category_fruit
import familyapp.composeapp.generated.resources.category_grain
import familyapp.composeapp.generated.resources.category_undefined
import familyapp.composeapp.generated.resources.category_vegetables
import familyapp.composeapp.generated.resources.fruits_svgrepo_com
import familyapp.composeapp.generated.resources.ic_grain
import familyapp.composeapp.generated.resources.percentage_measurement
import familyapp.composeapp.generated.resources.quant_type_amount
import familyapp.composeapp.generated.resources.quant_type_percentage
import familyapp.composeapp.generated.resources.quant_type_volume
import familyapp.composeapp.generated.resources.quant_type_weight
import familyapp.composeapp.generated.resources.volume_measurement
import familyapp.composeapp.generated.resources.weight_measurement
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class StockpileListModel(
    val name: String,
    val value: String,
    val category: StockpileItemCategory,
    val limitValue: String,
    val quantType: StockpileQuantType,
    val isInDeficit: Boolean,
    val imageUrl: String? = null
)


@Serializable
sealed class StockpileItemCategory(val name: String) {

    abstract fun getIconRes(): DrawableResource

    abstract fun getNameRes(): StringResource

    companion object {
        fun getByName(name: String): StockpileItemCategory {
            return when (name) {
                Vegetables.name -> Vegetables
                Candy.name -> Candy
                Grain.name -> Grain
                Fruit.name -> Fruit
                else -> Undefined
            }
        }
    }
}

data object Vegetables : StockpileItemCategory("Vegetables") {
    override fun getIconRes(): DrawableResource {
        return Res.drawable.fruits_svgrepo_com
    }

    override fun getNameRes(): StringResource {
        return Res.string.category_vegetables
    }
}

data object Candy : StockpileItemCategory("Candy") {
    override fun getIconRes(): DrawableResource {
        return Res.drawable.fruits_svgrepo_com
    }

    override fun getNameRes(): StringResource {
        return Res.string.category_candy
    }
}

data object Grain : StockpileItemCategory("Grain") {
    override fun getIconRes(): DrawableResource {
        return Res.drawable.ic_grain
    }

    override fun getNameRes(): StringResource {
        return Res.string.category_grain
    }
}

data object Fruit : StockpileItemCategory("Fruit") {
    override fun getIconRes(): DrawableResource {
        return Res.drawable.fruits_svgrepo_com
    }

    override fun getNameRes(): StringResource {
        return Res.string.category_fruit
    }
}

data object Undefined : StockpileItemCategory("Undefined") {
    override fun getIconRes(): DrawableResource {
        return Res.drawable.fruits_svgrepo_com
    }

    override fun getNameRes(): StringResource {
        return Res.string.category_undefined

    }
}

@Serializable
sealed class StockpileQuantType(private val range: Pair<Float, Float>, val name: String) {
    fun evalValueInRange(value: Float): Boolean {
        return value > range.first && value < range.second
    }

    abstract fun getMeasurementRes(): StringResource

    abstract fun getNameRes(): StringResource

    companion object {
        fun getByName(name: String): StockpileQuantType = when (name) {
            Volume.name -> Volume
            Amount.name -> Amount
            Percentage.name -> Percentage
            Weight.name -> Weight
            else -> Amount
        }
    }
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

