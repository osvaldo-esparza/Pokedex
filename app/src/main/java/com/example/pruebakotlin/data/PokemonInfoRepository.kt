package com.example.pruebakotlin.data

import com.example.pruebakotlin.data.model.PokemonInfo
import com.example.pruebakotlin.data.model.PokemonInfoProvider
import com.example.pruebakotlin.data.network.PokemonListService

class PokemonInfoRepository {

    private val api = PokemonListService()

    suspend fun getPokemonInfo(name:String): PokemonInfo? {
        val response = api.getPokemonInfo(name)
        if (response != null) {
            PokemonInfoProvider.pokpokemonInfo = response
        }
        return response!!
    }
}