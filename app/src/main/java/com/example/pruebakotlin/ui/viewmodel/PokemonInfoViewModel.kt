package com.example.pruebakotlin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebakotlin.data.model.PokemonInfo
import com.example.pruebakotlin.data.model.PokemonListModel
import com.example.pruebakotlin.domain.GetPokemonInfoUseCase
import kotlinx.coroutines.launch


class PokemonInfoViewModel: ViewModel() {
    val pokemonInfo = MutableLiveData<PokemonInfo>()
    val getPokemonInfoUseCase: GetPokemonInfoUseCase = GetPokemonInfoUseCase()

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading2: LiveData<Boolean> = _isLoading



    init {
        _isLoading.value = false
    }
    fun onCreate(name: String) {
        _isLoading.postValue(true)
        // Corrutina
        viewModelScope.launch {
            try {
                val result: PokemonInfo? = getPokemonInfoUseCase(name)
                result?.let { pokemonResponse ->
                    pokemonInfo.postValue(pokemonResponse)
                }
            } catch (e: Exception) {
                // Manejar la excepción si es necesario
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}