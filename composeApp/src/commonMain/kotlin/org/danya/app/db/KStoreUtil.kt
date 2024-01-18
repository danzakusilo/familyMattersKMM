package org.danya.app.db

import io.github.xxfast.kstore.KStore
import org.danya.app.db.models.Dishes

expect val store: KStore<Dishes>