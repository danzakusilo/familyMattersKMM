package com.danya.app.db

import com.danya.app.db.models.Dishes
import com.danya.app.models.Stockpile
import com.danya.app.models.StockpileItemModel
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.storage.storeOf

actual val dishesStore: KStore<Dishes> by lazy {
    storeOf("saved", emptySet())
}

actual val stockpileStore: KStore<Stockpile> by lazy {
    storeOf("stockpile", emptySet())
}