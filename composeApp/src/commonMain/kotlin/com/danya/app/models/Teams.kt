package com.danya.app.models

import kotlinx.serialization.Serializable

@Serializable
data class Family(
    val name: String,
    val pfpUrl: String,
    val members: List<User>
)