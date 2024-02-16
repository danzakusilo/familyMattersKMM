package com.danya.app.models

import kotlinx.serialization.Serializable

@Serializable
data class Stockpile(
    val values: Map<String, String>
)
