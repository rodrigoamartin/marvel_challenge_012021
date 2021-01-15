package com.rma.marvelchallenge.data.response.event

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rma.marvelchallenge.core.platform.DateUtils
import com.rma.marvelchallenge.data.response.character.Comics
import com.rma.marvelchallenge.data.response.character.toDomain
import com.rma.marvelchallenge.domain.Event

class Result {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("title")
    @Expose
    var title: String? = null


    @SerializedName("start")
    @Expose
    var start: String? = null

    @SerializedName("end")
    @Expose
    var end: String? = null

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: Thumbnail? = null


    @SerializedName("comics")
    @Expose
    var comics: Comics? = null
}

fun Result.toDomain(): Event {
    return Event(
        id = id?.toString() ?: "",
        name = title ?: "",
        startDate = DateUtils.toDate(start ?: ""),
        endDate = DateUtils.toDate(end ?: ""),
        iconUrl = thumbnail?.path ?: "",
        comics = comics?.toDomain() ?: mutableListOf()
    )
}