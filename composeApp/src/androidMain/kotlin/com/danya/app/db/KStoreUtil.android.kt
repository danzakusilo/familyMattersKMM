package com.danya.app.db

import com.danya.app.db.models.Dishes
import com.danya.app.di.appStorage
import com.danya.app.models.Stockpile
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import okio.Path.Companion.toPath

actual val dishesStore: KStore<Dishes> by lazy {
    storeOf("{$appStorage}/saved.json".toPath())
}

actual val stockpileStore: KStore<Stockpile> by lazy {
    storeOf("{$appStorage}/saved.json".toPath())
}