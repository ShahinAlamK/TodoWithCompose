package com.example.todo.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun DeleteTodo(isDelete: MutableState<Boolean>) {
    val scope = rememberCoroutineScope()
    if (isDelete.value)

        AlertDialog(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            textContentColor = MaterialTheme.colorScheme.onBackground,
            tonalElevation = 6.dp,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
            onDismissRequest = { /*TODO*/ },
            dismissButton = {
                TextButton(
                    shape = MaterialTheme.shapes.small,
                    onClick = { isDelete.value = false }) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                TextButton(
                    shape = MaterialTheme.shapes.small,
                    onClick = {}) {
                    Text(text = "Delete")
                }
            },
            title = { Text(text = "Warning", style = MaterialTheme.typography.bodyLarge) },
            text = {
                Text(
                    text = "Are you sure permanently deleted todo",
                    style = MaterialTheme.typography.bodyMedium
                )
            },

            )
}