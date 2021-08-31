package com.example.punk.entity

import com.example.punk.domain.BeerDetails
import com.google.gson.annotations.SerializedName

data class BeerDetailsResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("tagline") val tagline: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("food_pairing") val foodPairing: List<String>?
)

fun BeerDetailsResponse.toBeerDetails(): BeerDetails? {
    if (id != null
        && name != null
        && tagline != null
        && description != null
        && imageUrl != null
        && foodPairing != null
    ) {
        return BeerDetails(
            id = id,
            name = name,
            tagline = tagline,
            description = description,
            imageUrl = imageUrl,
            foodPairing = foodPairing
        )
    }
    return null
}
