package com.danya.app.db

import com.danya.app.db.models.Dishes
import io.github.xxfast.kstore.KStore

expect val store: KStore<Dishes>