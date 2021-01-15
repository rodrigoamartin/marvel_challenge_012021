package com.rma.marvelchallenge.domain

data class Character(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val iconUrl: String = "",
    val comics: List<Comic> = mutableListOf()
)