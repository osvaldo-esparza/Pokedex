package com.example.pruebakotlin.ui.adapter

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.pruebakotlin.R
import com.example.pruebakotlin.data.model.PokemonListModel

interface OnItemClickListener {
    fun onItemClick(pokemonName: PokemonListModel)
}

class PokemonListAdapter(private val listener: OnItemClickListener) :
    ListAdapter<PokemonListModel, PokemonListAdapter.PokemonViewHolder>(PokemonListDiffCallback()),Filterable {

    private var pokemonListFull = listOf<PokemonListModel>()

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pokemonNameTextView: TextView = itemView.findViewById(R.id.pokemonNameTextView)
        private val pokemonImageView : ImageView = itemView.findViewById(R.id.imageView)
        private val colorCV: CardView = itemView.findViewById(R.id.cardView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val pokemon = getItem(position)
                    listener.onItemClick(pokemon)
                }
            }
            pokemonListFull = currentList.toList()
        }

        fun bind(pokemonListModel: PokemonListModel) {
            pokemonNameTextView.text = pokemonListModel.name
            // Cargar la imagen desde la URL utilizando Glide
            // Cargar la imagen desde la URL utilizando Glide
            Glide.with(itemView.context)
                .load(pokemonListModel.getImageUrl())
                .centerInside()
                .into(object : CustomTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        // Configurar la imagen en el ImageView
                        pokemonImageView.setImageDrawable(resource)

                        // Convertir la imagen cargada a un mapa de bits
                        val bitmap = (resource as BitmapDrawable).bitmap

                        // Extraer el color dominante de la imagen utilizando la biblioteca Palette
                        val dominantColor = Palette.from(bitmap).generate().dominantSwatch?.rgb ?: Color.TRANSPARENT

                        // Aplicar el color dominante al fondo del CardView u otros elementos de la interfaz de usuario
                        colorCV.setCardBackgroundColor(dominantColor)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // Se llama cuando se borra la carga de la imagen
                    }
                })



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemonListModel: PokemonListModel = getItem(position)
        holder.bind(pokemonListModel)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<PokemonListModel>()
                val filterPattern = constraint.toString().trim().toLowerCase()

                if (filterPattern.isEmpty()) {
                    filteredList.addAll(pokemonListFull)
                } else {
                    for (pokemon in pokemonListFull) {
                        if (pokemon.name.toLowerCase().contains(filterPattern)) {
                            filteredList.add(pokemon)
                        }
                    }
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                submitList(results?.values as List<PokemonListModel>)
            }
        }
    }


}

class PokemonListDiffCallback : DiffUtil.ItemCallback<PokemonListModel>() {
    override fun areItemsTheSame(oldItem: PokemonListModel, newItem: PokemonListModel): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: PokemonListModel, newItem: PokemonListModel): Boolean {
        return oldItem == newItem
    }
}
