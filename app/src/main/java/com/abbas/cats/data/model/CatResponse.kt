package com.abbas.cats.data.model

data class CatResponse(
    val breeds: List<Breed>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)