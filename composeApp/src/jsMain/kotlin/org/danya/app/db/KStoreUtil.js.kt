package org.danya.app.db

import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.storage.storeOf
import org.danya.app.db.models.Dishes

actual val store: KStore<Dishes> by lazy {
    storeOf("saved", emptySet())
}