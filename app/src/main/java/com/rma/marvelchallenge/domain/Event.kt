package com.rma.marvelchallenge.domain

import java.util.*

data class Event(
    val id: String = "",
    val iconUrl: String = "",
    val name: String = "",
    val startDate: Date? = null,
    val endDate: Date? = null,
    val comics: List<Comic> = mutableListOf()
)