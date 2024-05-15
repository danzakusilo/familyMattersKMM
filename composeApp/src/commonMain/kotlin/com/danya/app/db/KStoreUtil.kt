package com.danya.app.db

import com.danya.app.db.models.Dishes
import com.danya.app.models.Stockpile
import com.danya.app.models.StockpileItemModel
import com.fleeksoft.ksoup.Ksoup
import io.github.xxfast.kstore.KStore

expect val dishesStore: KStore<Dishes>

expect val stockpileStore: KStore<Stockpile>