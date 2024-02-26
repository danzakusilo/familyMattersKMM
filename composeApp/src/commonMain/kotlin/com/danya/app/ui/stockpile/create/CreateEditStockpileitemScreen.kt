package com.danya.app.ui.stockpile.create

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.danya.app.models.StockpileItemModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class CreateEditStockpileItemScreen : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel<CreateEditStockpileItemScreenModel> { get() }
        var nameText: String by remember { mutableStateOf("") }
        var currentQuantText: String by remember { mutableStateOf("") }
        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                TextField(
                    value = nameText,
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 48.dp, start = 16.dp, end = 16.dp)
                        .border(
                            shape = RoundedCornerShape(4.dp),
                            width = 1.dp,
                            color = Color.Black
                        ),
                    onValueChange = {
                        nameText = it
                    }, placeholder = {
                        Text("Enter item name")
                    })

                TextField(
                    value = currentQuantText,
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 48.dp, start = 16.dp, end = 16.dp)
                        .border(
                            shape = RoundedCornerShape(4.dp),
                            width = 1.dp,
                            color = Color.Black
                        ),
                    onValueChange = {
                        currentQuantText = it
                    }, placeholder = {
                        Text("Enter item quantity present right now")
                    })

                Button(onClick = {
                    screenModel.postNewItem(
                        StockpileItemModel(
                            name = nameText,
                            quant = currentQuantText,
                            userId = Firebase.auth.currentUser?.uid.toString()
                        )
                    )
                }) {
                    Text("Post new item")
                }
            }
        }
    }
}
