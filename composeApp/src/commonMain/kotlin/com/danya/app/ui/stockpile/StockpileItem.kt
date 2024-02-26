package com.danya.app.ui.stockpile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.danya.app.models.StockpileItemModel
import com.seiko.imageloader.rememberImagePainter

@Composable
fun StockpileItem(modifier: Modifier = Modifier, stockpileItemModel: StockpileItemModel) {
    val imagePainter = stockpileItemModel.imageUrl?.let { rememberImagePainter(it) }
    Row(
        modifier = modifier.fillMaxWidth().border(
            shape = RoundedCornerShape(8.dp),
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        ).padding(8.dp)
    ) {
        Box(Modifier.clip(RoundedCornerShape(8.dp)).size(40.dp)) {
            if (imagePainter != null) {
                Image(
                    painter = imagePainter, contentDescription = null
                )
            }
        }
        Column {
            Text(stockpileItemModel.name, style = MaterialTheme.typography.bodyMedium)
            Text(
                stockpileItemModel.quant,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 14.dp)
            )
        }
    }
}
