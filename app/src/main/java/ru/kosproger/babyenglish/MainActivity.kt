package ru.kosproger.babyenglish

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppScreen()
        }
    }
}

@Preview
@Composable
fun AppScreen() {
    var currentScreen by rememberSaveable { mutableStateOf("main") } // Состояние для отслеживания текущего экрана
    BackHandler(enabled = currentScreen != "main") {
        currentScreen = "main"
    }
    when (currentScreen) {
        "main" -> MyScreen(
            onNavigateToAlphabet = { currentScreen = "alphabet" },
            onNavigateToNumbers = { currentScreen = "numbers" }
        )
        "alphabet" -> AlphabetScreen { currentScreen = "main" }
        "numbers" -> NumbersScreen { currentScreen = "main" }
    }
}

@Composable
fun MyScreen(onNavigateToAlphabet: () -> Unit, onNavigateToNumbers: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFCACA3C))
            .padding(top = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Hello",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color(0xFFCA3C3E),
                modifier = Modifier
                    .width(182.dp)
                    .height(52.dp)
                    .offset(x = 35.dp, y = 25.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.dragonpng),
                contentDescription = null,
                modifier = Modifier.size(390.dp)
            )

            Text(
                text = "Baby",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color(0xFFCA3C3E),
                modifier = Modifier
                    .width(285.dp)
                    .height(63.dp)
                    .offset(x = 90.dp, y = 10.dp)
            )
            Spacer(modifier = Modifier.height(48.dp))


            Button(
                onClick = onNavigateToAlphabet,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A70E1)), // Цвет кнопки
                modifier = Modifier
                    .size(width = 216.dp, height = 55.dp)
            ) {
                Text(
                    text = "A B C",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color.Red
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onNavigateToNumbers,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A70E1)), // Цвет кнопки
                modifier = Modifier
                    .size(width = 216.dp, height = 55.dp)
            ) {
                Text(
                    text = "1 2 3",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color.Green
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { /* Действие для третьей кнопки */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A70E1)), // Цвет кнопки
                modifier = Modifier
                    .size(width = 216.dp, height = 55.dp)
            ) {
                Text(
                    text = "Color's",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color.Cyan
                )
            }
        }
    }
}