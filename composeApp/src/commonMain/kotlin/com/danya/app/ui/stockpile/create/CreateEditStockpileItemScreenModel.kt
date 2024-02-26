package com.danya.app.ui.stockpile.create

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.danya.app.models.StockpileItemModel
import com.danya.app.ui.stockpile.StockpileApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class CreateEditStockpileItemScreenModel(private val api: StockpileApi) : ScreenModel,
    KoinComponent {
    fun postNewItem(input: StockpileItemModel) {
        screenModelScope.launch {
             api.postNewItem(input).collectLatest {
                 if (it.isSuccess){
                     println("!")
                 }
             }
        }
    }
}