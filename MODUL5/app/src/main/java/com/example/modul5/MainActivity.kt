package com.example.modul5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modul5.ui.FlowerDetailScreen
import com.example.modul5.ui.FlowerListScreen
import com.example.modul5.ui.theme.BirthFlowerzAppTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val isDark = themeViewModel.isDarkMode.collectAsState()

            BirthFlowerzAppTheme(darkTheme = isDark.value) {
                val navController = rememberNavController()

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Birth Flowers") },
                            actions = {
                                IconButton(onClick = { themeViewModel.toggleTheme() }) {
                                    Icon(
                                        imageVector = if (isDark.value) Icons.Filled.DarkMode else Icons.Filled.LightMode,
                                        contentDescription = "Toggle Theme"
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "main_screen",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("main_screen") {
                            FlowerListScreen(navController = navController)
                        }
                        composable("detail/{flowerIndex}") { backStackEntry ->
                            val index = backStackEntry.arguments?.getString("flowerIndex")?.toIntOrNull()
                            val flowerViewModel: BirthFlowerViewModel = viewModel()
                            val flowers = flowerViewModel.flowers.collectAsState().value
                            val flower = index?.let { flowers.getOrNull(it) }
                            flower?.let {
                                FlowerDetailScreen(flower = it)
                            }
                        }
                    }
                }
            }
        }
    }
}
