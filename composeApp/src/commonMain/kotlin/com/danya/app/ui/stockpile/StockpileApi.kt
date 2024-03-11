package com.danya.app.ui.stockpile

import co.touchlab.kermit.Logger
import com.danya.app.models.StockpileItemModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.where
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface StockpileApi {
    suspend fun postNewItem(input: StockpileItemModel): Flow<Result<Unit>>
    suspend fun getItems(): Flow<Result<List<StockpileItemModel>>>
}

class StockpileApiIml : StockpileApi {
    override suspend fun postNewItem(input: StockpileItemModel):
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
                    Firebase.firestore.collectionGroup(CollectionName).where {
                        any("userId".equalTo(Firebase.auth.currentUser?.uid))
                    }.get().documents.map {
                        it.data<StockpileItemModel>()
                    })
            } catch (e: Exception) {
                Logger.e(this::class.simpleName.toString(), e)
                Result.failure(e)
            }
            emit(request)
        }
    }

    companion object {
        const val CollectionName = "stockpile"
    }

}
