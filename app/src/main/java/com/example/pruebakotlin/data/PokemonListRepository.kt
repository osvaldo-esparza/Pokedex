package com.example.pruebakotlin.data

import com.example.pruebakotlin.data.model.PokemonInfo
import com.example.pruebakotlin.data.model.PokemonInfoProvider
import com.example.pruebakotlin.data.model.PokemonListModel
import com.example.pruebakotlin.data.model.PokemonListProvider
import com.example.pruebakotlin.data.model.PokemonResponse
import com.example.pruebakotlin.data.network.PokemonListService

class PokemonListRepository {

    private val api= PokemonListService()

    suspend fun getPokemonList(offset:Int,limit:Int):List<PokemonListModel>{
        val response = api.getPokemonList(offset,limit)
        PokemonListProvider.pokemonList = response
        return response
    }


}