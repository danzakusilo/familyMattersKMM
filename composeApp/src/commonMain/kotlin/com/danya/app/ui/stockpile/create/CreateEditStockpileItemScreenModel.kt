package com.danya.app.ui.stockpile.create

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import com.danya.app.models.StockpilePostModel
import com.danya.app.ui.stockpile.StockpileApi
import com.danya.app.ui.stockpile.StockpileInputModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
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

    private val _quantErrorVisible: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val quantErrorVisible: StateFlow<Boolean>
        get() = _quantErrorVisible

    private val _limitErrorVisible: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val limitErrorVisible: StateFlow<Boolean>
        get() = _limitErrorVisible

    private val _postErrorVisible: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val postErrorVisible: StateFlow<Boolean?>
        get() = _postErrorVisible

    fun postNewItem(input: StockpileInputModel) {
        val initialQuantValid = input.quantType.evalValueInRange(input.value.toFloat())
        val bottomLimitQuantValue =
            input.quantType.evalValueInRange(input.bottomLimitValue.toFloat())
        screenModelScope.launch {
            if (initialQuantValid && bottomLimitQuantValue) {
                val postObject = StockpilePostModel(
                    name = input.name,
                    imageUrl = null,
                    price = null,
                    quantType = input.quantType.name,
                    initialValue = input.value.toFloat(),
                    bottomLimitValue = input.bottomLimitValue.toFloat(),
                    category = input.selectedCategory.name,
                    userId = Firebase.auth.currentUser?.uid
                        ?: throw AssertionError("User not authenticated when posting new stockpile item")
                )
                Logger.d(tag = "post item data", messageString = postObject.toString())
                Logger.d(
                    tag = "auth data",
                    messageString = Firebase.auth.currentUser?.uid ?: "no user id found"
                )
                _quantErrorVisible.value = false
                api.postNewItem(postObject).collectLatest {
                    _newItemCreatedResponse.value = it.isSuccess
                    _postErrorVisible.value = it.isFailure
                }
            } else {
                _quantErrorVisible.value = true
            }
        }
    }

    fun editItem(id: String, input: StockpileInputModel) {
        val initialQuantValid = input.quantType.evalValueInRange(input.value.toFloat())
        val bottomLimitQuantValue =
            input.quantType.evalValueInRange(input.bottomLimitValue.toFloat())
        screenModelScope.launch {
            if (initialQuantValid && bottomLimitQuantValue) {
                val postObject = StockpilePostModel(
                    name = input.name,
                    imageUrl = null,
                    price = null,
                    quantType = input.quantType.name,
                    initialValue = input.value.toFloat(),
                    bottomLimitValue = input.bottomLimitValue.toFloat(),
                    category = input.selectedCategory.name,
                    userId = Firebase.auth.currentUser?.uid
                        ?: throw AssertionError("User not authenticated when posting new stockpile item")
                )
                _quantErrorVisible.value = false
                api.editItem(id, postObject).collectLatest {
                    _newItemCreatedResponse.value = it.isSuccess
                    _postErrorVisible.value = it.isFailure
                }
            } else {
                _quantErrorVisible.value = true
            }
        }
    }
}