package com.example.pruebakotlin.ui.viewmodel
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.pruebakotlin.data.model.PokemonInfo
import com.example.pruebakotlin.data.model.PokemonListModel
import com.example.pruebakotlin.data.model.PokemonResponse
import com.example.pruebakotlin.domain.GetPokemonListUseCase
import com.skydoves.androidribbon.ribbonView
import kotlinx.coroutines.launch
import kotlin.random.Random

class PokemonListViewModel : ViewModel() {
    val pokemonListViewModel = MutableLiveData<List<PokemonListModel>>()
    val getPokemonListUseCase: GetPokemonListUseCase = GetPokemonListUseCase()

    private var offset = 20
    private val limit = 20
    public var isLoading = false
    public var isLastPage = false

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading2: LiveData<Boolean> = _isLoading

    // Nueva variable para la navegación
    private var _navigator: PokemonListNavigator? = null

    // Método para establecer el objeto de navegación
    fun setNavigator(navigator: PokemonListNavigator) {
        _navigator = navigator
    }

    init {
        _isLoading.value = false
    }

    fun onCreate(offset: Int, limit: Int) {
        _isLoading.postValue(true)
        // Corrutina
        viewModelScope.launch {
            try {
                val result: List<PokemonListModel>? = getPokemonListUseCase(offset, limit)
                result?.let { pokemonResponse ->
                    pokemonListViewModel.postValue(pokemonResponse)
                }
            } catch (e: Exception) {
                // Manejar la excepción si es necesario
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun loadMorePokemon() {
        if (isLoading || isLastPage) return

        isLoading = true
        // Marcamos como cargando
        _isLoading.postValue(true)

        // Coroutine
        viewModelScope.launch {
            val result: List<PokemonListModel>? = getPokemonListUseCase(offset, limit)

            result?.let { pokemonResponse ->
                val currentList = pokemonListViewModel.value ?: emptyList()
                val updatedList = currentList + pokemonResponse
                pokemonListViewModel.postValue(updatedList)

                offset += limit
            }

            isLoading = false
            _isLoading.postValue(false)
            isLastPage = result.isNullOrEmpty()
        }
    }

    // Método para manejar el clic en un elemento de la lista
    fun onItemClick(pokemon: PokemonListModel) {
        _navigator?.navigateToPokemonDetail(pokemon)
    }





}
