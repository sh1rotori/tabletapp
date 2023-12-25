package com.example.myapplication22222

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay


@Composable
fun HomeScreen(navController: NavController) {
    // Создаем список баннеров
    val bannerImages = listOf(
        R.drawable.banner_0,
        R.drawable.banner_1,
        R.drawable.banner_2
    )

    // Индекс текущего отображаемого баннера
    var currentBannerIndex by remember { mutableStateOf(0) }

    // Запускаем цикл, который будет переключать изображения каждую секунду
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            currentBannerIndex = (currentBannerIndex + 1) % bannerImages.size
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Название страницы
        Text(text = "Главная", style = MaterialTheme.typography.titleLarge)

        // Баннер с картинками
        Banner(
            images = bannerImages,
            currentBannerIndex = currentBannerIndex
        )

        // Кнопки "Skills", "Photos", "Videos"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { navController.navigate("skills")}) {
                Icon(imageVector = Icons.Default.List, contentDescription = "Skills")
                Spacer(modifier = Modifier.width(40.dp))
                Text(text = "Skills")
            }

            Button(onClick = { navController.navigate("photos") }) {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Photos")
                Spacer(modifier = Modifier.width(40.dp))
                Text(text = "Photos")
            }

            Button(onClick = { navController.navigate("videos") }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Videos")
                Spacer(modifier = Modifier.width(40.dp))
                Text(text = "Videos")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Banner(images: List<Int>, currentBannerIndex: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        // Отображаем текущее изображение баннера
        Image(
            painter = painterResource(id = images[currentBannerIndex]),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
        )

        // Индикатор текущего изображения
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            for (i in images.indices) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .padding(4.dp)
                        .background(
                            if (i == currentBannerIndex) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                            },
                            shape = CircleShape
                        )
                )
            }
        }
    }
}