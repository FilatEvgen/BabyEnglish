package ru.kosproger.babyenglish

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var currentScreen by remember { mutableStateOf("main") } // Состояние для отслеживания текущего экрана

    when (currentScreen) {
        "main" -> MyScreen { currentScreen = "alphabet" } // Переход на экран алфавита
        "alphabet" -> AlphabetScreen { currentScreen = "main" } // Переход обратно на главный экран
    }
}

@Composable
fun MyScreen(onNavigateToAlphabet: () -> Unit) {
    // Ваш существующий код для первого экрана
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFCACA3C))
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
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onNavigateToAlphabet, // Переход на экран алфавита
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

                Button(
                    onClick = { /* Действие для второй кнопки */ },
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

                Button(
                    onClick = { /* Действие для третьей кнопки */ },
                    modifier = Modifier
                        .size(width = 216.dp, height = 55.dp)
                ) {
                    Text(
                        text = "Color`s",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color.Cyan
                    )
                }
            }
        }
    }
}