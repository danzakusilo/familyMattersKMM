package com.danya.app.ui.stockpile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.danya.app.ui.stockpile.create.CreateEditStockpileItemScreen
import com.danya.app.ui.stockpile.create.CreateEditStockpileItemScreen.Mode.Create
import com.danya.app.ui.stockpile.list.SearchScreen
import com.danya.app.ui.stockpile.list.StockpileItem
import compose.icons.FeatherIcons
import compose.icons.feathericons.Plus
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class StockpileScreen : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel<StockpileScreenModel> { get() }
        val list by screenModel.items.collectAsState()
        var dropdownMenuVisible by remember { mutableStateOf(false) }
        val navigator = LocalNavigator.currentOrThrow
        Box(Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(vertical = 52.dp, horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(list) { item ->
                    StockpileItem(Modifier, item)
                }
            }
            Box(
                Modifier.padding(vertical = 64.dp, horizontal = 36.dp).size(64.dp).clip(CircleShape)
                    .size(36.dp).align(Alignment.BottomEnd)
                    .background(MaterialTheme.colorScheme.primary).clickable(
                        indication = rememberRipple(), interactionSource = remember {
                            MutableInteractionSource()
                        }
                    ) {
                        dropdownMenuVisible = !dropdownMenuVisible
                    }
            ) {
                Image(
                    FeatherIcons.Plus,
                    modifier = Modifier.align(Alignment.Center),
                    contentDescription = null
                )
                DropdownMenu(
                    expanded = dropdownMenuVisible,
                    onDismissRequest = { dropdownMenuVisible = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Create item") },
                        onClick = { navigator.push(CreateEditStockpileItemScreen(Create)) }
                    )
                    DropdownMenuItem(
                        text = { Text("Search Tavria") },
                        onClick = { navigator.push(SearchScreen()) }
                    )
                }
            }
        }
    }
}
