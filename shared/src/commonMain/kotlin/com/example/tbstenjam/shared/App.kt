package com.example.tbstenjam.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.random.Random

@Composable
fun App() {
    LetsTBSten()
}


@Composable
private fun LetsTBSten() {
    var text by remember { mutableStateOf(generateRandomString()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(onClick = { text = generateRandomString() }) {
            Text("てべすてんを当てろ！")
        }
    }
}

private fun generateRandomString(): String {
    val characters = listOf("て", "べ", "す")
    val length = Random.nextInt(3, 8)
    val middlePart = (1..length).map { characters.random() }.joinToString("")
    return "て${middlePart}てん"
}

@Preview
@Composable
private fun DefaultPreview() {
    LetsTBSten()
}
