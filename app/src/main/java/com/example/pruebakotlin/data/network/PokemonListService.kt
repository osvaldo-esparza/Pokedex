package com.example.pruebakotlin.data.network

import com.example.pruebakotlin.core.RetrofitHelper
import com.example.pruebakotlin.data.model.PokemonInfo
import com.example.pruebakotlin.data.model.PokemonListModel
import com.example.pruebakotlin.data.model.PokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.create

class PokemonListService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getPokemonList(
        offset: Int,
        limit: Int
    ): List<PokemonListModel>{
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PokemonListApiClient::class.java).getListPokemon(offset, limit)
            (response.body()?.results ?: emptyList())
        }
    }

    suspend fun getPokemonInfo(name: String): PokemonInfo? {
        return withContext(Dispatchers.IO) {
            try {
                val response = retrofit.create(PokemonListApiClient::class.java).getPokemonInfo(name)
                if (response.isSuccessful) response.body() else null
            } catch (e: Exception) {
                null
            }
        }
    }
}
