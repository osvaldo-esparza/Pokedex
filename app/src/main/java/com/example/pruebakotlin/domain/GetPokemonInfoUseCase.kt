package com.example.pruebakotlin.domain

import com.example.pruebakotlin.data.PokemonInfoRepository
import com.example.pruebakotlin.data.PokemonListRepository
import com.example.pruebakotlin.data.model.PokemonInfo
import com.example.pruebakotlin.data.model.PokemonInfoProvider

class GetPokemonInfoUseCase {
    private val pokemoninfoRepository = PokemonInfoRepository()
    suspend operator fun invoke(name:String):PokemonInfo? = pokemoninfoRepository.getPokemonInfo(name)

}