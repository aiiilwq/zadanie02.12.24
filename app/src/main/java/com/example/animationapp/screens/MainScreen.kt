package com.example.animationapp.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


// Компоненты для анимации и иконок
@Composable
fun MainScreen() {
    var showText by remember { mutableStateOf(false) }
    var backgroundColor by remember { mutableStateOf(Color.LightGray) }
    var buttonSize by remember { mutableStateOf(1f) }

    // Анимация смены фона
    val animatedBackgroundColor by animateColorAsState(
        targetValue = backgroundColor,
        animationSpec = tween(durationMillis = 1000)
    )

    // Анимация кнопки (масштабирование)
    val buttonScale by animateFloatAsState(
        targetValue = buttonSize,
        animationSpec = tween(durationMillis = 300)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(animatedBackgroundColor)
            .padding(16.dp)
    ) {
        // Верхняя кнопка: показать/скрыть текст
        IconButton(
            onClick = {
                showText = !showText
                buttonSize = if (buttonSize == 1f) 1.2f else 1f
            },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .scale(buttonScale)
        ) {
            Icon(Icons.Default.Visibility, contentDescription = "Показать/Скрыть текст", tint = Color.White)
        }

        // Анимация текста
        AnimatedVisibility(
            visible = showText,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)) + scaleIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500)) + scaleOut(animationSpec = tween(durationMillis = 500)),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "Приветственное сообщение!",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }

        // Правая кнопка: смена фона
        IconButton(
            onClick = {
                backgroundColor = Color(
                    red = (0..255).random() / 255f,
                    green = (0..255).random() / 255f,
                    blue = (0..255).random() / 255f
                )
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(Icons.Default.ColorLens, contentDescription = "Сменить фон", tint = Color.White)
        }

        // Нижняя кнопка: сброс анимаций
        IconButton(
            onClick = {
                showText = false
                buttonSize = 1f
                backgroundColor = Color.LightGray
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Icon(Icons.Default.Refresh, contentDescription = "Сбросить анимации", tint = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}
