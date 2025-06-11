package com.example.modul5.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.modul5.model.FlowerRemote
import com.example.modul5.BirthFlowerViewModel

@Composable
fun FlowerListScreen(navController: NavController) {
    val viewModel: BirthFlowerViewModel = viewModel()
    val flowers = viewModel.flowers.collectAsState()
    val flowerList = flowers.value

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        itemsIndexed(flowerList) { index: Int, flower: FlowerRemote ->
        FlowerItem(
                flower = flower,
                index = index,
                navController = navController
            )
        }
    }
}

@Composable
fun FlowerItem(
    flower: FlowerRemote,
    index: Int,
    navController: NavController
) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(flower.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = flower.name,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = flower.name,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = flower.symbol,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(flower.wikiLink))
                        context.startActivity(intent)
                    }) {
                        Text(text = "Wiki", fontSize = 12.sp)
                    }

                    Button(onClick = {
                        navController.navigate("detail/$index")
                    }) {
                        Text(text = "Detail", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
