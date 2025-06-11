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

    val flowersList = listOf(
        Flowers("January - Carnation",
            "Symbolizes admiration and love. Represents deep affection and distinction.",
            "https://en.wikipedia.org/wiki/Dianthus_caryophyllus",
            R.drawable.carnation),

        Flowers("February - Iris",
            "Represents wisdom and hope. Known for elegance and royal symbolism.",
            "https://en.wikipedia.org/wiki/Iris_(plant)",
            R.drawable.iris),

        Flowers("March - Daffodil",
            "Symbol of new beginnings and hope. Blooms mark the start of spring.",
            "https://en.wikipedia.org/wiki/Narcissus_(plant)",
            R.drawable.daffodil),

        Flowers("April - Sweet Pea",
            "Conveys bliss and goodbyes. Fragrant flower representing gentle charm.",
            "https://en.wikipedia.org/wiki/Lathyrus_odoratus",
            R.drawable.sweetpea),

        Flowers("May - Lily of the Valley",
            "Represents purity and joy. White blooms symbolize fresh starts.",
            "https://en.wikipedia.org/wiki/Lily_of_the_valley",
            R.drawable.lilyofthevalley),

        Flowers("June - Rose",
            "Classic symbol of love and beauty. Each color has its own meaning.",
            "https://en.wikipedia.org/wiki/Rose",
            R.drawable.rose),

        Flowers("July - Larkspur",
            "Stands for lightness and joy. Bright spikes represent strong bonds.",
            "https://en.wikipedia.org/wiki/Delphinium",
            R.drawable.larkspur),

        Flowers("August - Poppy",
            "Symbolizes peace and remembrance. Red petals evoke reflection.",
            "https://en.wikipedia.org/wiki/Poppy",
            R.drawable.poppy),

        Flowers("September - Aster",
            "Represents love and faith. Star-shaped petals show lasting beauty.",
            "https://en.wikipedia.org/wiki/Aster",
            R.drawable.aster),

        Flowers("October - Marigold",
            "Stands for warmth and creativity. Golden tones bring optimism.",
            "https://en.wikipedia.org/wiki/Tagetes",
            R.drawable.marigold),

        Flowers("November - Peony",
            "Symbolizes romance and honor. Lush blooms express elegance.",
            "https://en.wikipedia.org/wiki/Peony",
            R.drawable.peony),

        Flowers("December - Narcissus",
            "Represents self-love and rebirth. A flower of renewal and growth.",
            "https://en.wikipedia.org/wiki/Narcissus_(plant)",
            R.drawable.narcissus)
    )

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(flowersList) { flower ->
            FlowerItem(flower, navController)
        }
    }
}

@Composable
fun FlowerItem(flower: Flowers, navController: NavController) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Image(
                painter = painterResource(id = flower.image),
                contentDescription = flower.name,
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = flower.name,
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold, fontSize = 22.sp)
                )
                Text(text = flower.symbol)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),  // Add spacing between buttons
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically  // Ensure both buttons align vertically
                ) {
                    Button(
                        onClick = { openLink(flower.wikiLink, context) },
                        modifier = Modifier.weight(1f)  // Make buttons take equal width
                    ) {
                        Text(text = "Learn More")
                    }
                    Button(
                        onClick = {
                            navController.navigate("flower_detail/${flowersList.indexOf(flower)}")
                        },
                        modifier = Modifier.weight(1f)  // Make buttons take equal width
                    ) {
                        Text(text = "Detail", fontWeight = FontWeight.Bold)
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
