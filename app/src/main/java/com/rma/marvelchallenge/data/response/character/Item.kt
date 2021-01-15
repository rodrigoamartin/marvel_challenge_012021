package com.rma.marvelchallenge.data.response.character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rma.marvelchallenge.domain.Comic

class Item {
    @SerializedName("resourceURI")
    @Expose
    var resourceURI: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null
}

fun Item.toDomain(): Comic{
    return Comic(name = name ?: "")
}