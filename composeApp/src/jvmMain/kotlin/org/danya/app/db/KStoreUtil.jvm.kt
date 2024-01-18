package org.danya.app.db

import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import okio.Path.Companion.toPath
import org.danya.app.db.models.Dishes
import org.danya.app.di.appStorage

actual val store: KStore<Dishes> by lazy {
    storeOf("{$appStorage}/saved.json".toPath(), emptySet())
}