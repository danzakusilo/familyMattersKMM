package org.danya.app.api.login

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class LoginApi : KoinComponent {
    val client: HttpClient = get()

    suspend fun hello(): String {
        val response = client.get("https://ktor.io/docs/")
        return response.bodyAsText()
    }
}