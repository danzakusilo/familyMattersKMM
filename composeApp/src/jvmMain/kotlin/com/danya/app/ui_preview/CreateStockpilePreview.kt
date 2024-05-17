package com.danya.app.ui_preview

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.danya.app.theme.AppTheme
import com.danya.app.ui.stockpile.create.CreateEditStockpileItemScreen
import com.danya.app.ui.stockpile.create.CreateEditStockpileItemScreen.Mode.Create

@Preview
@Composable
fun CreateEditStockpileItemScreenPreview() {
    CreateEditStockpileItemScreen(Create)
}