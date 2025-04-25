package com.example.tipapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipapp.ui.theme.TipAppTheme
import com.example.tipapp.ui.theme.TipScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TipTimeApp()
                }
            }
        }
    }
}

@Composable
fun TipTimeApp() {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color(0xFF6200EE)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Tip Time",
                fontSize = 20.sp,
                color = Color.White
            )
        }
        TipScreen(modifier = Modifier.padding(16.dp))
    }
}
