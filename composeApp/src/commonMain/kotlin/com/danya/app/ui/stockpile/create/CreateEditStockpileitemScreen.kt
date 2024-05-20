package com.danya.app.ui.stockpile.create

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.danya.app.ui.stockpile.StockpileInputModel
import com.danya.app.ui.stockpile.list.Amount
import com.danya.app.ui.stockpile.list.Percentage
import com.danya.app.ui.stockpile.list.StockpileQuantType
import com.danya.app.ui.stockpile.list.Undefined
import com.danya.app.ui.stockpile.list.Volume
import com.danya.app.ui.stockpile.list.Weight
import familyapp.composeapp.generated.resources.Res
import familyapp.composeapp.generated.resources.bottom_limit_title
import familyapp.composeapp.generated.resources.initial_value_title
import familyapp.composeapp.generated.resources.submit
import familyapp.composeapp.generated.resources.title
import familyapp.composeapp.generated.resources.value_out_of_range
import org.jetbrains.compose.resources.stringResource
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class CreateEditStockpileItemScreen(private val mode: Mode) : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel<CreateEditStockpileItemScreenModel> { get() }
        var nameText: String by remember { mutableStateOf("") }
        var currentQuantText: String by remember { mutableStateOf("") }
        var limitText: String by remember { mutableStateOf("") }
        var limitDropdownVisible by remember { mutableStateOf(false) }
        var initialDropdownVisible by remember { mutableStateOf(false) }
        var currentQuantType by remember { mutableStateOf<StockpileQuantType>(Amount) }
        val isInitialQuantError by screenModel.quantErrorVisible.collectAsState()
        val isLimitError by screenModel.limitErrorVisible.collectAsState()
        Box(modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 36.dp)) {
            Column {
                Text(
                    modifier = Modifier.padding(top = 24.dp).align(Alignment.Start),
                    style = MaterialTheme.typography.headlineSmall,
                    text = stringResource(Res.string.title)
                )
                OutlinedTextField(
                    modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(top = 16.dp),
                    shape = RoundedCornerShape(8.dp),
                    value = nameText,
                    onValueChange = {
                        nameText = it
                    },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.headlineLarge,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    supportingText = {
                        if (isInitialQuantError) {
                            Text(
                                text = stringResource(Res.string.value_out_of_range),
                                color = Color.Red
                            )
                        }
                    }
                )
                /*
                Initial value title and input
                 */
                Text(
                    modifier = Modifier.padding(top = 16.dp).align(Alignment.Start),
                    text = stringResource(Res.string.initial_value_title),
                    style = MaterialTheme.typography.headlineSmall
                )
                Row(modifier = Modifier.padding(top = 16.dp)) {
                    OutlinedTextField(
                        modifier = Modifier.wrapContentHeight()
                            .weight(7f),
                        shape = RoundedCornerShape(8.dp),
                        value = currentQuantText,
                        onValueChange = {
                            currentQuantText = it
                        },
                        singleLine = true,
                        textStyle = MaterialTheme.typography.headlineLarge,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Black
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        supportingText = {
                            if (isInitialQuantError) {
                                Text(
                                    text = stringResource(Res.string.value_out_of_range),
                                    color = Color.Red
                                )
                            }
                        }
                    )
                    Box(
                        modifier = Modifier.padding(start = 16.dp)
                            .size(width = 80.dp, height = 70.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .border(
                                1.dp, Color.Black, RoundedCornerShape(6.dp)
                            ).weight(3f).clickable {
                                initialDropdownVisible = true
                            }
                    ) {
                        Text(
                            text = stringResource(currentQuantType.getMeasurementRes()),
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.align(Alignment.Center)
                        )
                        QuantTypeSelectDropdown(
                            expanded = initialDropdownVisible,
                            onDismiss = { initialDropdownVisible = false }
                        ) { quantType ->
                            currentQuantType = quantType
                        }
                    }
                }
                Text(
                    modifier = Modifier.padding(top = 16.dp).align(Alignment.Start),
                    text = stringResource(Res.string.bottom_limit_title),
                    style = MaterialTheme.typography.headlineSmall
                )
                Row(modifier = Modifier.padding(top = 16.dp)) {
                    OutlinedTextField(
                        modifier = Modifier.wrapContentHeight()
                            .weight(7f),
                        shape = RoundedCornerShape(8.dp),
                        value = limitText,
                        onValueChange = {
                            limitText = it
                        },
                        singleLine = true,
                        textStyle = MaterialTheme.typography.headlineLarge,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Black
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        supportingText = {
                            if (isLimitError) {
                                Text(
                                    text = stringResource(Res.string.value_out_of_range),
                                    color = Color.Red
                                )
                            }
                        }
                    )
                    Box(
                        modifier = Modifier.padding(start = 16.dp)
                            .size(width = 80.dp, height = 70.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .border(
                                1.dp, Color.Black, RoundedCornerShape(6.dp)
                            )
                            .weight(3f)
                            .clickable {
                                limitDropdownVisible = true
                            }
                    ) {
                        Text(
                            text = stringResource(currentQuantType.getMeasurementRes()),
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.align(Alignment.Center)
                        )
                        QuantTypeSelectDropdown(
                            expanded = limitDropdownVisible,
                            onDismiss = { limitDropdownVisible = false }
                        ) { quantType ->
                            currentQuantType = quantType
                        }
                    }
                }
            }
            Button(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp))
                    .align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(),
                onClick = {
                    screenModel.postNewItem(
                        StockpileInputModel(
                            name = nameText,
                            bottomLimitValue = limitText,
                            value = currentQuantText,
                            quantType = currentQuantType,
                            selectedCategory = Undefined
                        )
                    )
                },
                enabled = currentQuantText.isNotBlank() && nameText.isNotBlank() && limitText.isNotBlank()
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.headlineLarge,
                    text = stringResource(Res.string.submit)
                )
            }
        }
    }

    @Composable
    private fun QuantTypeSelectDropdown(
        expanded: Boolean,
        onDismiss: () -> Unit,
        onOptionSelected: (StockpileQuantType) -> Unit
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismiss
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(Volume.getNameRes())) },
                onClick = {
                    onOptionSelected(Volume)
                    onDismiss()
                }
            )
            DropdownMenuItem(
                text = { Text(stringResource(Amount.getNameRes())) },
                onClick = {
                    onOptionSelected(Amount)
                    onDismiss()
                }
            )
            DropdownMenuItem(
                text = { Text(stringResource(Percentage.getNameRes())) },
                onClick = {
                    onOptionSelected(Percentage)
                    onDismiss()
                }
            )

            DropdownMenuItem(
                text = { Text(stringResource(Weight.getNameRes())) },
                onClick = {
                    onOptionSelected(Weight)
                    onDismiss()
                }
            )
        }
    }

    enum class Mode {
        Edit, Create
    }
}