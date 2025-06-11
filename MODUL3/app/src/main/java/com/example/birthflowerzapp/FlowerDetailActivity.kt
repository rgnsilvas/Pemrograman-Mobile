package com.example.birthflowerzapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment

class FlowerDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val flowerId = intent.getIntExtra("flowerId", 0)
            val flower = flowersList[flowerId]
            FlowerDetailScreen(flower = flower)
        }
    }
}

@Composable
fun FlowerDetailScreen(flower: Flowers) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(listOf(flower)) { currentFlower ->
            Image(
                painter = painterResource(id = currentFlower.image),
                contentDescription = currentFlower.name,
                modifier = Modifier.size(300.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = currentFlower.name,
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold, fontSize = 28.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Detail:", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))

            Text(text = currentFlower.symbol, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
