package com.rma.marvelchallenge.data

import com.rma.marvelchallenge.data.response.character.GetCharactersResponse
import com.rma.marvelchallenge.data.response.event.GetEventsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("characters")
    fun getCharacters(
        @Query("offset") offset : Int,
        @Query("limit") limit: Int
    ): Call<GetCharactersResponse>

    @GET("characters/{id}")
    fun getCharacter(
        @Path("id") id : String
    ): Call<GetCharactersResponse>

    @GET("events")
    fun getEvents(
        @Query("limit") limit: Int,
        @Query("orderBy") orderBy: String
    ): Call<GetEventsResponse>
}