package com.danya.app.ui.stockpile.create

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.danya.app.models.StockpileItemModel
import com.danya.app.ui.stockpile.StockpileApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class CreateEditStockpileItemScreenModel(private val api: StockpileApi) : ScreenModel,
    KoinComponent {
    private val _newItemCreatedResponse: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val newItemCreatedResponse: StateFlow<Boolean?>
        get() = _newItemCreatedResponse

    fun postNewItem(input: StockpileItemModel) {
        screenModelScope.launch {
            api.postNewItem(input).collectLatest {
                _newItemCreatedResponse.value = it.isSuccess
            }
        }
    }
}