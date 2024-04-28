package com.danya.app.ui.stockpile

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import com.danya.app.models.StockpileItemModel
import com.fleeksoft.ksoup.Ksoup
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class StockpileScreenModel(private val api: StockpileApi) : ScreenModel, KoinComponent {

    val items = MutableStateFlow<List<StockpileItemModel>>(emptyList())

    init {
        getItems()
    }

    private fun getItems() {
        screenModelScope.launch {
            api.getItems().collectLatest {
                if (it.isSuccess)
                    items.value = it.getOrNull() ?: emptyList()
            }
        }
    }

    suspend fun getItems2(query: String, size: Int) {
        val ktorClient: HttpClient = get()
        val response = ktorClient.get(
            "https://www.tavriav.ua/catalog/search/?query=$query"
        )
        Logger.d( tag = "getItems2", messageString =  response.bodyAsText())
        if (response.status == HttpStatusCode.OK) {
            val doc = Ksoup.parse(response.bodyAsText())
            Logger.d(tag = "ScrapingDoc", messageString = doc.children().map {
                it.className().toString()
            }.joinToString { "" })
        }
    }
}
