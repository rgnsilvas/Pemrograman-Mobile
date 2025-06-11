package com.example.modul5.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlowerRemote(
    @SerialName("name") val name: String,
    @SerialName("symbol") val symbol: String,
    @SerialName("wikiLink") val wikiLink: String,
    @SerialName("imageUrl") val imageUrl: String
)
