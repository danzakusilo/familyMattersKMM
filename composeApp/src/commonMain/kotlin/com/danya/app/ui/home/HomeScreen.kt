package com.danya.app.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.danya.app.ui.stockpile.StockpileScreen
import compose.icons.FeatherIcons
import compose.icons.feathericons.BookOpen
import compose.icons.feathericons.Calendar
import compose.icons.feathericons.Lock
import compose.icons.feathericons.ShoppingBag
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class HomeScreen : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel<HomeScreenModel> { get() }
        val navigator = LocalNavigator.currentOrThrow
        Box(modifier = Modifier.fillMaxSize()) {
            ClickableBoxWithIcon(
                icon = FeatherIcons.ShoppingBag,
                text = "Stockpile",
                modifier = Modifier.fillMaxSize(0.5f).align(Alignment.TopStart),
            ) {
                navigator.push(StockpileScreen())
            }
            ClickableBoxWithIcon(
                icon = FeatherIcons.BookOpen,
                text = "Recipes",
                modifier = Modifier.fillMaxSize(0.5f).align(Alignment.TopEnd),
            ) {

            }
            ClickableBoxWithIcon(
                icon = FeatherIcons.Calendar,
                text = "Events",
                modifier = Modifier.fillMaxSize(0.5f).align(Alignment.BottomStart),
            ) {

            }
            ClickableBoxWithIcon(
                icon = FeatherIcons.Lock,
                text = "Coming soon",
                modifier = Modifier.fillMaxSize(0.5f).align(Alignment.BottomEnd),
            ) {

            }

        }
    }

    @Composable
    private fun ClickableBoxWithIcon(
        icon: ImageVector,
        modifier: Modifier,
        text: String,
        onClick: () -> Unit,
    ) {
        Box(modifier = modifier.clickable(
            indication = rememberRipple(), onClick = onClick, interactionSource = remember {
                MutableInteractionSource()
            }
        )) {
            Column(Modifier.align(Alignment.Center)) {
                Icon(
                    imageVector = icon, contentDescription = null, modifier =
                    Modifier.size(60.dp).align(Alignment.CenterHorizontally)
                )
                Text(
                    text = text,
                    fontSize = 24.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 4.dp)
                )
            }
        }

    }
}
