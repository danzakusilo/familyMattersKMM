package com.danya.app.ui.stockpile.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import compose.icons.FeatherIcons
import compose.icons.feathericons.AlertCircle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun StockpileItem(modifier: Modifier = Modifier, model: StockpileListModel) {
    // todo use imageUrl
    val imagePainter = model.imageUrl?.let { rememberImagePainter(it) }
    Row(
        modifier.fillMaxWidth().height(60.dp).clip(
            RoundedCornerShape(8.dp)
        ).border(1.dp, Color.Black, RoundedCornerShape(8.dp))
            .padding(vertical = 12.dp, horizontal = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.size(36.dp).align(Alignment.CenterVertically),
            painter = painterResource(model.category.getIconRes()),
            contentDescription = ""
        )
        // title of item
        Text(
            modifier = Modifier.align(Alignment.CenterVertically).padding(start = 16.dp)
                .weight(6f, true),
            text = model.name,
            style = MaterialTheme.typography.headlineMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Row(Modifier.wrapContentWidth().align(Alignment.CenterVertically).weight(4f, false)) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = model.value,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = stringResource(model.quantType.getMeasurementRes()),
                style = MaterialTheme.typography.headlineSmall,
            )
            if (model.isInDeficit) {
                Image(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    imageVector = FeatherIcons.AlertCircle,
                    contentDescription = ""
                )
            }
        }
    }
}
