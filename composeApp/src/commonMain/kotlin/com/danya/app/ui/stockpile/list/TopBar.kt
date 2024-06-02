package com.danya.app.ui.stockpile.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.Filter
import compose.icons.feathericons.Search
import familyapp.composeapp.generated.resources.Res
import familyapp.composeapp.generated.resources.search
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TopBar(
    modifier: Modifier,
    onBack: () -> Unit,
    onSearch: (String) -> Unit,
    onFilter: () -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    var dropdownMenuVisible by remember { mutableStateOf(false) }
    var searchJob: Job? by remember { mutableStateOf(null) }
    val scope = rememberCoroutineScope()

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onBack() }) {
            Icon(FeatherIcons.ArrowLeft, contentDescription = "Back")
        }

        OutlinedTextField(
            value = searchText,
            onValueChange = { newText ->
                searchText = newText
                searchJob?.cancel()
                searchJob = scope.launch {
                    delay(300L)  // debounce timeOut
                    onSearch(newText)
                }
            },
            modifier = Modifier.weight(1f).padding(start = 8.dp),
            placeholder = { Text(stringResource(Res.string.search)) },
            leadingIcon = { Icon(FeatherIcons.Search, contentDescription = "Search") }
        )

        Box {
            IconButton(onClick = { dropdownMenuVisible = true }) {
                Icon(FeatherIcons.Filter, contentDescription = "Filter")
            }
            DropdownMenu(
                expanded = dropdownMenuVisible,
                onDismissRequest = { dropdownMenuVisible = false }
            ) {
                Categories.forEach { category ->
                    DropdownMenuItem(
                        text = {
                            Text(text = category.name, modifier = Modifier.padding(start = 8.dp))
                        },
                        leadingIcon = {
                            Image(
                                modifier = Modifier.size(22.dp),
                                painter = painterResource(category.getIconRes()),
                                contentDescription = category.name
                            )
                        },
                        onClick = { onFilter() })
                }
            }
        }
    }
}