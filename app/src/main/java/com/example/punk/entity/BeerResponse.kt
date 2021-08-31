package com.example.punk.entity

import com.example.punk.domain.Beer
import com.google.gson.annotations.SerializedName

data class BeerResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("tagline") val tagline: String?,
    @SerializedName("image_url") val imageUrl: String?
)

fun BeerResponse.toBeer(): Beer? {
    if (id != null
        && name != null
        && tagline != null
        && imageUrl != null
    ) {
        return Beer(
            id = id,
            name = name,
            tagline = tagline,
            imageUrl = imageUrl
        )
    }
    return null
}
