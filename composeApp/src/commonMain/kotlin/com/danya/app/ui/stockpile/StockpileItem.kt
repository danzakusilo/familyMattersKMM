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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danya.app.models.StockpileItemModel
import com.seiko.imageloader.rememberImagePainter
import compose.icons.FeatherIcons
import compose.icons.feathericons.ShoppingCart

@Composable
fun StockpileItem(modifier: Modifier = Modifier, stockpileItemModel: StockpileItemModel) {
    // todo use imageUrl
    val imagePainter = stockpileItemModel.imageUrl?.let { rememberImagePainter(it) }
    Row(
        modifier = modifier.fillMaxWidth().border(
            shape = RoundedCornerShape(8.dp),
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        ).padding(8.dp)
    ) {
        Box(
            Modifier.clip(RoundedCornerShape(8.dp)).border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.primary
            ).size(40.dp)
        ) {
            Image(
                imageVector = FeatherIcons.ShoppingCart, contentDescription = null, modifier.align(
                    Alignment.Center
                ).size(25.dp)
            )
        }
        Row(Modifier.padding(start = 14.dp)) {
            Text(
                stockpileItemModel.name,
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                "-",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                stockpileItemModel.quant.value.toString(),
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}
