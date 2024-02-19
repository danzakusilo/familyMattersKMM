package com.danya.app.ui.stockpile

import com.danya.app.models.StockpileItemModel
import com.danya.app.ui.stockpile.model.CreateStockPileItemUIModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface StockpileApi {
    suspend fun postNewItem(input: CreateStockPileItemUIModel): Flow<Result<Unit>>
    suspend fun getItems(): Flow<Result<List<StockpileItemModel>>>
}

class StockpileApiIml : StockpileApi {
    override suspend fun postNewItem(input: CreateStockPileItemUIModel):
            Flow<Result<Unit>> {
        return flow {
            val request = try {
                Result.success(
                    Firebase.firestore.collection(CollectionName).document.set(input)
                )
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(request)
        }
    }

    override suspend fun getItems(): Flow<Result<List<StockpileItemModel>>> {
        return flow {
            val request = try {
                Result.success(
                    Firebase.firestore.collection(CollectionName).get().documents.map {
                        it.data<StockpileItemModel>()
                    }
                )
            } catch (e: Exception){
                Result.failure(e)
            }
            emit(request)
        }
    }

    companion object {
        const val CollectionName = "stockpile"
    }

}
