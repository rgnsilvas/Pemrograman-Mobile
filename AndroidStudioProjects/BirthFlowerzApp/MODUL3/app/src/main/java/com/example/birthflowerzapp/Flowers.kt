package com.example.birthflowerzapp

import androidx.annotation.DrawableRes

data class Flowers(
    val name: String,
    val symbol: String,
    val wikiLink: String,
    @DrawableRes val image: Int
)

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

