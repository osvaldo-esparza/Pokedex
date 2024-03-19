package com.example.pruebakotlin.data.model

class PokemonInfoProvider {
    companion object{
        var pokpokemonInfo:PokemonInfo= PokemonInfo(
            id = 0,
            name = "",
            height = 0,
            weight = 0,
            experience = 0,
            stats = emptyList(),
            types = emptyList()
        )
    }
}