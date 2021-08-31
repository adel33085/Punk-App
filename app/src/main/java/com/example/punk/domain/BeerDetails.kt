package com.example.punk.domain

data class BeerDetails(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val imageUrl: String,
    val foodPairing: List<String>
)
