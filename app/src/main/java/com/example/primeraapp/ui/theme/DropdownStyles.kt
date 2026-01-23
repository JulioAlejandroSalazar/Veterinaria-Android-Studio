package com.example.primeraapp.ui.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun darkDropdownColors() = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
    focusedBorderColor = Color.White,
    unfocusedBorderColor = Color.White,
    disabledBorderColor = Color.White,
    focusedLabelColor = Color.White,
    unfocusedLabelColor = Color.White,
    focusedTextColor = Color.White,
    unfocusedTextColor = Color.White,
    focusedTrailingIconColor = Color.White,
    unfocusedTrailingIconColor = Color.White
)
