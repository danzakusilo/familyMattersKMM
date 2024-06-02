package com.danya.app.ui.stockpile.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danya.app.util.trimDecimals
import compose.icons.FeatherIcons
import compose.icons.feathericons.AlertCircle
import familyapp.composeapp.generated.resources.Res
import familyapp.composeapp.generated.resources.list_deficit_required_prefix
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

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
                Text(
                    text = model.value.trimDecimals() + " " + stringResource(model.quantType.getMeasurementRes()),
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 16.sp
                )
                if (model.isInDeficit) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            imageVector = FeatherIcons.AlertCircle,
                            modifier = Modifier.size(20.dp).padding(end = 4.dp),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.Red)
                        )
                        Text(
                            text = stringResource(Res.string.list_deficit_required_prefix) + model.needToBuy.trimDecimals() + " " + stringResource(
                                model.quantType.getMeasurementRes()
                            ),
                            style = MaterialTheme.typography.headlineSmall,
                            fontSize = 16.sp
                        )
                    }
                }
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
