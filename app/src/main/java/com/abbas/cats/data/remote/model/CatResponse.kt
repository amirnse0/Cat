package com.abbas.cats.data.remote.model

data class CatResponse(
    val breeds: List<BreedResponse>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)