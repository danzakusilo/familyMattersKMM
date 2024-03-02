package com.danya.app.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String?,
    val pfpUrl: String?
){
    companion object {
        const val CollectionName = "users"
    }
}