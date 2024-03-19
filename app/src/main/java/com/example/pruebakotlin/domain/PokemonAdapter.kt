package com.example.pruebakotlin.domain

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebakotlin.R
import com.example.pruebakotlin.data.model.PokemonListModel

interface OnItemClickListener {
    fun onItemClick(pokemonName: String)
}

class PokemonAdapter(private val pokemonList: List<PokemonListModel>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>(){

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pokemonNameTextView: TextView = itemView.findViewById(R.id.pokemonNameTextView)
        private val pokemonImageView : ImageView = itemView.findViewById(R.id.imageView)
        private val colorCV: CardView = itemView.findViewById(R.id.cardView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val pokemon = pokemonList[position]
                    listener.onItemClick(pokemon.name)
                }
            }
        }

        fun bind(pokemonModel: PokemonListModel) {
            pokemonNameTextView.text = pokemonModel.name
            Glide.with(itemView.context)
                .load(pokemonModel.getImageUrl())
                .centerInside()
                .into(pokemonImageView)
            //colorCV.setBackgroundColor(pokemonModel.colorCV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item,parent,false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemonModel: PokemonListModel = pokemonList[position]
        holder.bind(pokemonModel)
    }
}
