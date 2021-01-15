package com.rma.marvelchallenge.data.response.character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rma.marvelchallenge.domain.Character

class Result {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null


    @SerializedName("thumbnail")
    @Expose
    var thumbnail: Thumbnail? = null


    @SerializedName("comics")
    @Expose
    var comics: Comics? = null
}

fun Result.toDomain(): Character {
    return Character(
        id = id?.toString() ?: "",
        name = name ?: "",
        description = description ?: "",
        iconUrl = thumbnail?.path ?: "",
        comics = comics?.toDomain() ?: mutableListOf()
    )
}