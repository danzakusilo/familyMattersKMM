package com.danya.app.ui.stockpile

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.danya.app.models.StockpileItemModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StockpileScreenModel(private val api: StockpileApi) : ScreenModel {

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
}
