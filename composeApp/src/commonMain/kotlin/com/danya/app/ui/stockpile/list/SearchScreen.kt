package com.danya.app.ui.stockpile.list

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class SearchScreen : Screen, KoinComponent {
    @Composable
    @Preview
    override fun Content() {
        val screenModel = rememberScreenModel<SearchScreenModel> { get() }
        var searchText by remember { mutableStateOf("") }
        val items by remember { mutableStateOf(listOf<StockpileListModel>()) }
        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp)) {
            TextField(
                value = searchText,
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 48.dp, start = 16.dp, end = 16.dp)
                    .border(
                        shape = RoundedCornerShape(4.dp),
                        width = 1.dp,
                        color = Color.Black
                    ),
                onValueChange = {
                    searchText = it
                }, placeholder = {
                    Text("Search for an item")
                })

            LazyColumn(modifier = Modifier.padding(top = 12.dp)) {
                items(items) { item ->
                    StockpileItem(Modifier, item)
                }
            }
        }
    }
}