package com.danya.app.ui.stockpile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class StockpileScreen : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel<StockpileScreenModel> { get() }
        Box(Modifier.fillMaxSize()) {
            Text("Stockpile", Modifier.align(Alignment.Center))
        }
    }
}