package com.vee.musicapp.data.models

import java.util.UUID

class Category(
    val id: String = "${UUID.randomUUID()}",
    val name: String,
    val type: String,
    val movies: List<Movie>
)
