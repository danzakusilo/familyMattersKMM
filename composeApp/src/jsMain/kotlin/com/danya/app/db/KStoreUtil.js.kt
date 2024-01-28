package com.danya.app.db

import com.danya.app.db.models.Dishes
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.storage.storeOf

actual val store: KStore<Dishes> by lazy {
    storeOf("saved", emptySet())
}