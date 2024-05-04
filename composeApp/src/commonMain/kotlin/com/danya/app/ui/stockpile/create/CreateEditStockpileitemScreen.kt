package com.danya.app.ui.stockpile.create

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.danya.app.models.Amount
import com.danya.app.models.Percentage
import com.danya.app.models.StockpileQuantType
import com.danya.app.models.Volume
import com.danya.app.ui.common.clearBackground
import familyapp.composeapp.generated.resources.Res
import familyapp.composeapp.generated.resources.initial_value_title
import familyapp.composeapp.generated.resources.title
import org.jetbrains.compose.resources.stringResource
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class CreateEditStockpileItemScreen : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel<CreateEditStockpileItemScreenModel> { get() }
        var nameText: String by remember { mutableStateOf("") }
        var currentQuantText: String by remember { mutableStateOf("") }
        var dropdownMenuVisible by remember { mutableStateOf(false) }
        var currentQuantType by remember { mutableStateOf<StockpileQuantType>(Amount) }
        val scope = rememberCoroutineScope()
        Box(modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 36.dp)) {
            Column {
                Text(
                    modifier = Modifier.padding(top = 24.dp).align(Alignment.Start),
                    style = MaterialTheme.typography.headlineSmall,
                    text = stringResource(Res.string.title)
                )
                TextField(
                    modifier = Modifier
                        .padding(top = 16.dp).fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .border(1.dp, Color.Black, RoundedCornerShape(6.dp))
                        .background(Color.Transparent),
                    value = nameText, onValueChange = {
                        nameText = it
                    }, colors = TextFieldDefaults.clearBackground()
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp).align(Alignment.Start),
                    text = stringResource(Res.string.initial_value_title),
                    style = MaterialTheme.typography.headlineSmall
                )
                Row(modifier = Modifier.padding(top = 16.dp)) {
                    TextField(
                        modifier = Modifier.size(80.dp).clip(RoundedCornerShape(6.dp))
                            .border(1.dp, Color.Black, RoundedCornerShape(6.dp)),
                        value = currentQuantText,
                        onValueChange = {
                            currentQuantText = it
                        },
                        singleLine = true,
                        textStyle = MaterialTheme.typography.bodyLarge,
                        colors = TextFieldDefaults.clearBackground()
                    )
                    Box(Modifier.padding(start = 24.dp).clickable {
                        dropdownMenuVisible = !dropdownMenuVisible
                    }) {
                        Text(
                            text = stringResource(currentQuantType.getNameRes()),
                            Modifier.size(80.dp).clip(RoundedCornerShape(6.dp))
                                .border(1.dp, Color.Black, RoundedCornerShape(6.dp))
                        )
                        DropdownMenu(
                            expanded = dropdownMenuVisible,
                            onDismissRequest = { dropdownMenuVisible = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text(stringResource(Volume.getNameRes())) },
                                onClick = {
                                    currentQuantType = Volume
                                    dropdownMenuVisible = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(Amount.getNameRes())) },
                                onClick = {
                                    currentQuantType = Amount
                                    dropdownMenuVisible = false

                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(Percentage.getNameRes())) },
                                onClick = {
                                    currentQuantType = Percentage
                                    dropdownMenuVisible = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
