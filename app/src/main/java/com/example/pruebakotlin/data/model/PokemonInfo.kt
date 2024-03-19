package com.example.pruebakotlin.data.model

import com.google.gson.annotations.SerializedName
import kotlin.random.Random

data class PokemonInfo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("base_experience")
    val experience: Int,
    @SerializedName("stats") val stats:List<Stats>,
    @SerializedName("types")
    val types: List<TypeResponse>,
    val hp: Int = Random.nextInt(MAX_HP),
    val HP: Int = MAX_HP,
    val attack: Int = Random.nextInt(MAX_ATTACK),
    val ATTACK: Float = MAX_ATTACK.toFloat(),
    val defense: Int = Random.nextInt(MAX_DEFENSE),
    val DEFENSE: Float = MAX_DEFENSE.toFloat(),
    val speed: Int = Random.nextInt(MAX_SPEED),
    val SPEED: Float = MAX_SPEED.toFloat(),
    val exp: Int = Random.nextInt(MAX_EXP),
    val EXP : Float = MAX_EXP.toFloat()
) {



    fun getIdString(): String = String.format("#%03d", id)
    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
    fun getHpString(): String = " $hp/$MAX_HP"
    fun getAttackString(): String = " $attack/$MAX_ATTACK"
    fun getDefenseString(): String = " $defense/$MAX_DEFENSE"
    fun getSpeedString(): String = " $speed/$MAX_SPEED"
    fun getExpString(): String = " $exp/$MAX_EXP"

    fun getImageUrl(url:String): String {
        // Obtener el ID del Pokémon de la URL
        val id = url.split("/").dropLast(1).last()
        // Construir la URL de la imagen utilizando la URL base de la API de PokeAPI v2
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }

    data class TypeResponse(
        @SerializedName("slot")
        val slot: Int,
        @SerializedName("type")
        val type: Type
    )

    data class Type(
        @SerializedName("name")
        val name: String
    )

    data class Stats(
        @SerializedName("base_stat")val base_stat:String,
        @SerializedName("effort")val  effort:String,
        @SerializedName("stat")val stat:Stat
    )
    data class Stat(
        @SerializedName("name")val name:String,
        @SerializedName("url")val url:String
    )

    companion object {
        const val MAX_HP = 300
        const val MAX_ATTACK = 300
        const val MAX_DEFENSE = 300
        const val MAX_SPEED = 300
        const val MAX_EXP = 1000
    }
}
