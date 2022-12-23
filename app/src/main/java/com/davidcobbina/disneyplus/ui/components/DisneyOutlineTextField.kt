package com.davidcobbina.disneyplus.ui.components

import android.util.Log
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp

@Composable
fun DisneyOutlineTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    textStyle: TextStyle = MaterialTheme.typography.titleMedium.copy(
        color = Color.White,
        fontSize = 18.sp
    ),
    singleLine: Boolean = false,
    onValueChange: (TextFieldValue) -> Unit,
    label: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    textFieldColors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = Color.White,
        cursorColor = Color.White,
        focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
        unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
        disabledBorderColor = MaterialTheme.colorScheme.primaryContainer,
        focusedLabelColor = MaterialTheme.colorScheme.secondaryContainer,
        disabledLabelColor = Color.DarkGray,
        unfocusedLabelColor = Color.DarkGray,
    ),
) {

    var textState by remember { mutableStateOf(TextFieldValue(value)) }

    OutlinedTextField(
        modifier = modifier,
        textStyle = textStyle,
        value = textState,
        onValueChange = {
            textState = it
            onValueChange(it)
        },
        singleLine = singleLine,
        label = label,
        colors = textFieldColors,
        keyboardOptions = keyboardOptions,
    )
}