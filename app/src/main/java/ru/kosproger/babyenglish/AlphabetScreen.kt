package ru.kosproger.babyenglish

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AlphabetScreen(onBack: () -> Unit) {
    val letters = ('A'..'Z').toList()
    var selectedLetter by remember { mutableStateOf<Char?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog && selectedLetter != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Буква ${selectedLetter}") },
            text = {
                Column {
                    Text(text = "Нажмите на кнопку, чтобы прослушать звук.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Button(onClick = { playSound(selectedLetter!!) }) {
                            Text("Прослушать")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { /* Здесь можно добавить функционал для проговаривания */ }) {
                            Text("Проговорить")
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Закрыть")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Английский алфавит",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(letters) { letter ->
                Button(
                    onClick = {
                        selectedLetter = letter
                        showDialog = true
                    },
                    modifier = Modifier
                        .size(60.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF6A70E1))
                ) {
                    Text(text = letter.toString(), fontSize = 20.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text(text = "Назад", fontSize = 20.sp)
        }
    }
}

fun playSound(selectedLetter: Char) {

}
@Preview
@Composable
fun PreviewAlphabetScreen() {
    AlphabetScreen(onBack = {})
}