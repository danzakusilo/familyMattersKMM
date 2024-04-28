package com.danya.app.ui.searchList

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Logger.Companion
import com.fleeksoft.ksoup.Ksoup
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

interface SearchAPI {
    suspend fun getItems(query: String, size: Int)
}

class SearchAPIImpl(private val ktorClient: HttpClient) : SearchAPI {
    override suspend fun getItems(query: String, size: Int) {
        val response = ktorClient.get(
            "https://www.tavriav.ua/catalog/search/$query"
        )
        if (response.status == HttpStatusCode.OK) {
            val doc = Ksoup.parse(response.bodyAsText())
            Logger.d(tag = "ScrapingDoc", messageString = doc.children().toString())
        }
    }
}