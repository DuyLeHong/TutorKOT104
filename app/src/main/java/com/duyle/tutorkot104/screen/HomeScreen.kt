package com.duyle.tutorkot104.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    Column (Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Quan ly san pham", style = MaterialTheme.typography.titleLarge)

        LazyColumn {

        }
    }
}