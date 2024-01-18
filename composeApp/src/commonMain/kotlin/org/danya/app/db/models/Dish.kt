package org.danya.app.db.models

import kotlinx.serialization.Serializable

typealias Dishes = Set<Dish>

@Serializable
data class Dish(
    val name: String
)
