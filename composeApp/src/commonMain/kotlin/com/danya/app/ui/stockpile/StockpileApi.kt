package com.danya.app.ui.stockpile

import co.touchlab.kermit.Logger
import com.danya.app.models.StockpileItemModel
import com.danya.app.models.StockpilePostModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.where
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface StockpileApi {
    suspend fun postNewItem(input: StockpilePostModel): Flow<Result<Unit>>
    suspend fun getItems(): Flow<Result<List<StockpileItemModel>>>
    suspend fun editItem(id: String, input: StockpilePostModel): Flow<Result<Unit>>
}

class StockpileApiIml : StockpileApi {
    override suspend fun postNewItem(input: StockpilePostModel):
            Flow<Result<Unit>> {
        return flow {
            val request = try {
                val newId = Firebase.firestore.collection(CollectionName).document.id
                Result.success(
                    Firebase.firestore.collection(CollectionName).document(newId).set(input)
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
                        it.data<StockpileItemModel>().copy(
                            uid = it.id
                        )
                    })
            } catch (e: Exception) {
                Logger.e(this::class.simpleName.toString(), e)
                Result.failure(e)
            }
            emit(request)
        }
    }

    override suspend fun editItem(
        id: String,
        input: StockpilePostModel
    ): Flow<Result<Unit>> {
        return flow {
            val request = try {
                Result.success(
                    Firebase.firestore.collection(CollectionName).document(id).set(
                        input
                    )
                )
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
