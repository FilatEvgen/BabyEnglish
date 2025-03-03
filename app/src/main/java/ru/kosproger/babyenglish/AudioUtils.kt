package ru.kosproger.babyenglish

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer

fun playSound(resId: Int, context: Context) {
    val mediaPlayer = MediaPlayer.create(context, resId)
    mediaPlayer.setOnCompletionListener { mp ->
        mp.release()
    }
    mediaPlayer.start()
}

fun getSoundResIdForLetter(letter: Char): Int {
    return when (letter.uppercaseChar()) {
        'A' -> R.raw.a
        'B' -> R.raw.b
        'C' -> R.raw.c
        'D' -> R.raw.d
        'E' -> R.raw.e
        'F' -> R.raw.f
        'G' -> R.raw.g
        'H' -> R.raw.h
        'I' -> R.raw.i
        'J' -> R.raw.j
        'K' -> R.raw.k
        'L' -> R.raw.l
        'M' -> R.raw.m
        'N' -> R.raw.n
        'O' -> R.raw.o
        'P' -> R.raw.p
        'Q' -> R.raw.q
        'R' -> R.raw.r
        'S' -> R.raw.s
        'T' -> R.raw.t
        'U' -> R.raw.u
        'V' -> R.raw.v
        'W' -> R.raw.w
        'X' -> R.raw.x
        'Y' -> R.raw.y
        'Z' -> R.raw.z
        else -> throw IllegalArgumentException("Invalid letter: $letter")
    }
}

fun getSoundResIdForNumber(number: Int): Int {
    return when (number) {
        1 -> R.raw.en_num_01
        2 -> R.raw.en_num_02
        3 -> R.raw.en_num_03
        4 -> R.raw.en_num_04
        5 -> R.raw.en_num_05
        6 -> R.raw.en_num_06
        7 -> R.raw.en_num_07
        8 -> R.raw.en_num_08
        9 -> R.raw.en_num_09
        10 -> R.raw.en_num_10
        11 -> R.raw.en_num_11
        12 -> R.raw.en_num_12
        13 -> R.raw.en_num_13
        14 -> R.raw.en_num_14
        15 -> R.raw.en_num_15
        16 -> R.raw.en_num_16
        17 -> R.raw.en_num_17
        18 -> R.raw.en_num_18
        19 -> R.raw.en_num_19
        20 -> R.raw.en_num_20
        else -> throw IllegalArgumentException("Invalid number: $number")
    }
}

// Функция для сравнения текста с учетом гибкости
fun compareTextFlexible(spokenText: String, correctText: String): Boolean {
    val spoken = spokenText
        .lowercase()
        .replace(" ", "")
        .replace("[^a-z0-9]".toRegex(), "")
    val correct = correctText
        .lowercase()
        .replace(" ", "")
        .replace("[^a-z0-9]".toRegex(), "")

    return spoken == correct
}

// Функция для запуска распознавания речи
fun startVoiceRecognition(
    context: Context,
    correctText: String,
    onResult: (Boolean, String) -> Unit
) {
    val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
        putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
    }

    speechRecognizer.setRecognitionListener(object : RecognitionListener {
        override fun onReadyForSpeech(params: Bundle?) {
            // Подготовка к распознаванию
        }

        override fun onBeginningOfSpeech() {
            // Начало речи
        }

        override fun onRmsChanged(rmsdB: Float) {
            // Изменение уровня громкости
        }

        override fun onBufferReceived(buffer: ByteArray?) {
            // Получение буфера
        }

        override fun onEndOfSpeech() {
            // Конец речи
        }

        override fun onError(error: Int) {
            // Обработка ошибок
            onResult(false, "")
        }

        override fun onResults(results: Bundle?) {
            val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            if (matches != null && matches.isNotEmpty()) {
                val spokenText = matches[0]
                val isCorrect = compareTextFlexible(spokenText, correctText)
                onResult(isCorrect, spokenText)
            } else {
                onResult(false, "") // Если результатов нет, считаем, что распознавание не удалось
            }
        }

        override fun onPartialResults(partialResults: Bundle?) {
            // Обработка частичных результатов
            val partialMatches = partialResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            if (partialMatches != null && partialMatches.isNotEmpty()) {
                val partialText = partialMatches[0]
                println("Частичный результат: $partialText")
            }
        }

        override fun onEvent(eventType: Int, params: Bundle?) {
            // Обработка событий
        }
    })

    speechRecognizer.startListening(intent)
}