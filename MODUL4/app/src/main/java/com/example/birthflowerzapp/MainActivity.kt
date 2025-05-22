package com.example.birthflowerzapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import com.example.birthflowerzapp.ui.theme.BirthFlowerzAppTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import android.content.Intent
import android.net.Uri
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import com.example.birthflowerzapp.BirthFlowerViewModel
import com.example.birthflowerzapp.BirthFlowerViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BirthFlowerzAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "main_screen") {
                    composable("main_screen") {
                        MainScreen(navController = navController)
                    }
                    composable("flower_detail/{flowerId}") { backStackEntry ->
                        val flowerId = backStackEntry.arguments?.getString("flowerId")?.toInt() ?: 0
                        FlowerDetailScreen(flower = flowersList[flowerId])
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    val viewModel: BirthFlowerViewModel = viewModel(factory = BirthFlowerViewModelFactory())
    val flowers = viewModel.flowers.collectAsState()

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(flowers.value.size) { index ->
            val flower = flowers.value[index]
            FlowerItem(
                flower = flower,
                index = index,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}

@Composable
fun FlowerItem(
    flower: Flowers,
    index: Int,
    viewModel: BirthFlowerViewModel,
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
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = flower.image),
                contentDescription = flower.name,
                modifier = Modifier
                    .size(140.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = flower.name,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Detail:",
                    style = MaterialTheme.typography.bodyMedium.copy(
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
                    Button(
                        onClick = { openLink(flower.wikiLink, context) },
                        modifier = Modifier
                            .width(75.dp)
                    ) {
                        Text(
                            text = "Wiki",
                            fontSize = 12.sp,
                            maxLines = 1
                        )
                    }

                    Button(
                        onClick = {
                            viewModel.selectFlower(index) // Logging klik detail
                            navController.navigate("flower_detail/$index")
                        },
                        modifier = Modifier
                            .width(90.dp)
                    ) {
                        Text(
                            text = "Detail",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}

fun openLink(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}
