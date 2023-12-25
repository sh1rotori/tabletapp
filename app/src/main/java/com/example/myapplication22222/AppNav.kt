package com.example.myapplication22222

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val items = listOf(
        DrawerItem(Icons.Default.Home, "Home", "home"),
        DrawerItem(Icons.Default.List, "Skills", "skills"),
        DrawerItem(Icons.Default.DateRange, "Photos", "photos"),
        DrawerItem(Icons.Default.MailOutline, "Videos", "videos")
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val selectItem = remember {
        mutableStateOf(items[0])
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Image(
                    painterResource(id = R.drawable.icon_app),
                    contentDescription = "icon app",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),  // Уменьшаем высоту логотипа
                    contentScale = ContentScale.Fit  // Изменяем параметр contentScale
                )
                Spacer(modifier = Modifier.height(15.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        label = {
                            Text(text = item.title)
                        },
                        selected = selectItem.value == item,
                        icon = {
                            Icon(imageVector = item.imageVector, contentDescription = item.title)
                        },
                        onClick = {
                            scope.launch {
                                selectItem.value = item
                                drawerState.close()
                                navController.navigate(item.route)
                            }
                        }
                    )
                }
            }
        },
        content = {
            AppNavigationGraph(navController = navController)
        }
    )
}

data class DrawerItem(
    val imageVector: ImageVector,
    val title: String,
    val route: String
)

@Composable
fun AppNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("skills") {
            SkillScreen()
        }
        composable("photos") {

        }
        composable("videos") {
            // TODO: Create VideosScreen() and replace HomeScreen() with it
        }
    }
}