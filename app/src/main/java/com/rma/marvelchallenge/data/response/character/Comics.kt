package com.rma.marvelchallenge.data.response.character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rma.marvelchallenge.domain.Comic

class Comics {
    @SerializedName("available")
    @Expose
    var available: Int? = null

    @SerializedName("collectionURI")
    @Expose
    var collectionURI: String? = null

    @SerializedName("items")
    @Expose
    var items: List<Item>? = null

    @SerializedName("returned")
    @Expose
    var returned: Int? = null
}

fun Comics.toDomain(): List<Comic>{
    val comics = mutableListOf<Comic>()
    for (item in items ?: listOf()){
        val comic = item.toDomain()
        comics.add(comic)
    }
    return comics
}