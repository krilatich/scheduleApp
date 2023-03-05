package com.example.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.schedule.data.SearchMenuStateHolder

@Composable
fun SearchMenu(stateHolder: SearchMenuStateHolder) {

    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 5.dp, end = 5.dp)
    ) {
        EntryField(
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            value = stateHolder.value,
            onValueChange = {
                stateHolder.value = it
                stateHolder.updateItems((1..5).map {
                    "option ${stateHolder.value}"
                })
            },
        )
        if (stateHolder.items.isNotEmpty())
            LazyColumn(
                Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(stateHolder.items.take(5)) { s ->
                    Row() {
                        Icon(Icons.Default.Search, contentDescription = "search")
                        Text(
                            s, modifier = Modifier
                                .clickable(onClick = {
                                    stateHolder.value = s
                                    stateHolder.updateItems(listOf())
                                    focusManager.clearFocus()
                                })
                                .background(
                                    MaterialTheme.colors.primary,
                                    shape = RoundedCornerShape(5.dp)
                                )
                        )
                    }
                }
            }

    }
}


@Composable
fun EntryField(
    modifier: Modifier = Modifier,
    label: String = "",
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    value: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = value,
        singleLine = true,
        modifier = modifier.height(60.dp),
        onValueChange = onValueChange,
        label = {
            Text(
                label,
                color = Color.Gray,
                style = MaterialTheme.typography.body1
            )
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            unfocusedBorderColor = MaterialTheme.colors.onBackground
        ),
        visualTransformation = visualTransformation
    )

}