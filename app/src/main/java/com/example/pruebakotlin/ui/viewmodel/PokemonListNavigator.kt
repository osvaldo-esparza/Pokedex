package com.example.pruebakotlin.ui.viewmodel

import com.example.pruebakotlin.data.model.PokemonListModel

interface PokemonListNavigator {
    fun navigateToPokemonDetail(pokemon: PokemonListModel)
}
