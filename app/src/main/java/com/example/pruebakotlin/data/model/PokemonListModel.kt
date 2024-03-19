package com.example.pruebakotlin.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PokemonListModel (
    @SerializedName("name")val name:String,
    @SerializedName("url")val url:String
    ): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    fun getImageUrl(): String {
        // Obtener el ID del Pokémon de la URL
        val id = url.split("/").dropLast(1).last()
        // Construir la URL de la imagen utilizando la URL base de la API de PokeAPI v2
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonListModel> {
        override fun createFromParcel(parcel: Parcel): PokemonListModel {
            return PokemonListModel(parcel)
        }

        override fun newArray(size: Int): Array<PokemonListModel?> {
            return arrayOfNulls(size)
        }
    }
}