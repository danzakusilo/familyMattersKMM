package com.danya.app.ui_preview

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.danya.app.models.StockpileItemModel

@Preview
@Composable
fun StockpileItemPreview() {
    val item = StockpileItemModel(
        name = "Sour cream", quant = "1pack", price = "50 hrn", imageUrl = "" +
                "https://static.tavriav.ua/resize_150x150/2/2/8/8/2288fe40809024b99fc5161a7b1e1d3cda28ad62_10470_photo_main.png"
    )
    com.danya.app.ui.stockpile.StockpileItem(stockpileItemModel = item)
}