package ru.kosproger.babyenglish

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@Composable
fun AlphabetScreen(onBack: () -> Unit) {
    val letters = ('A'..'Z').toList()
    var selectedLetter by remember { mutableStateOf<Char?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var recognitionResult by remember { mutableStateOf("") }
    var isCorrect by remember { mutableStateOf(false) }
    var isRecording by remember { mutableStateOf(false) } // Новое состояние для отслеживания записи
    val context = LocalContext.current

    // Запрос разрешений на использование микрофона
    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
        }
    }

    if (showDialog && selectedLetter != null) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                isCorrect = false
                recognitionResult = ""
                isRecording = false // Сброс состояния записи
            },
            title = { Text(text = "Буква ${selectedLetter}") },
            text = {
                Column {
                    Text(text = "Нажмите на кнопку, чтобы прослушать звук.")
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(onClick = { playSound(getSoundResIdForLetter(selectedLetter!!), context) }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_audio),
                                contentDescription = "Прослушать",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Button(
                            onClick = {
                                isRecording = true // Устанавливаем состояние записи
                                startVoiceRecognition(context, selectedLetter.toString()) { result, spokenText ->
                                    isRecording = false // Сбрасываем состояние записи
                                    isCorrect = compareTextFlexible(spokenText, selectedLetter.toString())
                                    recognitionResult = spokenText
                                }
                            },
                            colors = ButtonDefaults.buttonColors(if (isRecording) Color.Red else Color.Gray) // Изменяем цвет кнопки
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_micro),
                                contentDescription = "Проговорить",
                                modifier = Modifier.size(24.dp)
                            )
                            if (isRecording) {
                                Text("Запись...", color = Color.White) // Добавляем текст для индикации записи
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    if (recognitionResult.isNotEmpty()) {
                        Text(text = "Вы сказали: $recognitionResult")
                        if (isCorrect) {
                            Text(text = "Правильно!", color = Color.Green)
                        } else {
                            Text(text = "Неправильно, попробуйте еще раз.", color = Color.Red)
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    isCorrect = false
                    recognitionResult = ""
                    isRecording = false // Сброс состояния записи
                }) {
                    Text("Закрыть")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
            .padding(36.dp),
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
                        playSound(getSoundResIdForLetter(letter), context)
                        showDialog = true
                        isCorrect = false
                        recognitionResult = ""
                    },
                    modifier = Modifier
                        .size(60.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF6A70E1))
                ) {
                    Text(text = letter.toString(), fontSize = 20.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onBack) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "Назад",
                modifier = Modifier.size(48.dp)
            )
        }
    }
}