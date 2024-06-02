package com.danya.app.ui.stockpile.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danya.app.util.trimDecimals
import com.seiko.imageloader.rememberImagePainter
import compose.icons.FeatherIcons
import compose.icons.feathericons.AlertCircle
import familyapp.composeapp.generated.resources.Res
import familyapp.composeapp.generated.resources.list_deficit_required_prefix
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
            // show value decimals only if they are not zero
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = model.value.trimDecimals(),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = stringResource(model.quantType.getMeasurementRes()).trimDecimals(),
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

@Composable
fun StockpileListItem(modifier: Modifier, model: StockpileListModel) {
    ListItem(
        modifier = modifier,
        headlineContent = {
            Text(
                text = model.name,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 24.sp
            )
        },
        supportingContent = {
            Column(horizontalAlignment = Alignment.Start) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (model.isInDeficit) {
                        Image(
                            imageVector = FeatherIcons.AlertCircle,
                            modifier = Modifier.size(20.dp).padding(end = 4.dp),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.Red)
                        )
                    }
                    Text(
                        text = model.value.trimDecimals() + " " + stringResource(model.quantType.getMeasurementRes()),
                        style = MaterialTheme.typography.headlineSmall,
                        fontSize = 16.sp
                    )
                }
                if (model.isInDeficit)
                    Text(
                        text = stringResource(Res.string.list_deficit_required_prefix) + model.needToBuy.trimDecimals() + " " + stringResource(
                            model.quantType.getMeasurementRes()
                        ),
                        style = MaterialTheme.typography.headlineSmall,
                        fontSize = 16.sp
                    )
            }
        },
        trailingContent = {
            Image(
                modifier = Modifier.size(48.dp),
                painter = painterResource(model.category.getIconRes()),
                contentDescription = ""
            )
        }
    )
}
