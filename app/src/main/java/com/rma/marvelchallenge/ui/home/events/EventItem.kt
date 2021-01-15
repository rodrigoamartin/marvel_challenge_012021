package com.rma.marvelchallenge.ui.home.events

import com.rma.marvelchallenge.domain.Comic
import java.util.*

data class EventItem(
    val id: String = "",
    val iconUrl: String = "",
    val name: String = "",
    val startDate: Date? = null,
    val endDate: Date? = null,
    val comics: List<Comic> = mutableListOf(),
    var isExpanded: Boolean = false
)