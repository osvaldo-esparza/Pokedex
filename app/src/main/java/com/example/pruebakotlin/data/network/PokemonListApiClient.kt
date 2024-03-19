package com.example.pruebakotlin.data.network

import com.example.pruebakotlin.data.model.PokemonInfo
import com.example.pruebakotlin.data.model.PokemonListModel
import com.example.pruebakotlin.data.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonListApiClient {
    @GET("pokemon")
    suspend fun getListPokemon(
        @Query("offset") offset: Int = 20,
        @Query("limit") limit: Int = 0
    ): Response<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(@Path("name") name: String): Response<PokemonInfo>
}