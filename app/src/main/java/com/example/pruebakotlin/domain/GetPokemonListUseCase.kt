package com.example.pruebakotlin.domain

import com.example.pruebakotlin.data.PokemonListRepository
import com.example.pruebakotlin.data.model.PokemonListModel
import com.example.pruebakotlin.data.model.PokemonResponse

class GetPokemonListUseCase {
    private val repository = PokemonListRepository()
    suspend operator fun invoke(offset:Int,limit:Int): List<PokemonListModel>? = repository.getPokemonList(offset,limit)

}