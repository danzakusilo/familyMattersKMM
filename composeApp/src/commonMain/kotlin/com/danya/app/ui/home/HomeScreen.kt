package com.danya.app.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class HomeScreen : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel<HomeScreenModel> { get() }
        Box(Modifier.fillMaxSize()) {
            Text(
                text = "This is Home screen",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 64.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}