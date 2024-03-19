package com.example.pruebakotlin.ui.view

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.pruebakotlin.R
import com.example.pruebakotlin.data.model.PokemonInfo
import com.example.pruebakotlin.databinding.PokemonDetailBinding
import com.example.pruebakotlin.ui.viewmodel.PokemonInfoViewModel
import com.skydoves.androidribbon.ribbonView
import com.skydoves.progressview.progressView
import kotlin.random.Random

class PokemonDetail : AppCompatActivity() {

    private lateinit var binding: PokemonDetailBinding

    private val pokemonInfo : PokemonInfoViewModel by viewModels()

    val typeColorMap = mapOf(
        "normal" to R.color.md_white_200,
        "electric" to R.color.md_yellow_200,
        "fire" to R.color.md_red_500,
        "water" to R.color.md_blue_500,
        "grass" to R.color.md_green_500,
        "ice" to R.color.md_light_blue_500,
        "fighting" to R.color.md_brown_500,
        "poison" to R.color.md_purple_500,
        "ground" to R.color.md_amber_500,
        "flying" to R.color.md_cyan_500,
        "psychic" to R.color.md_pink_500,
        "bug" to R.color.md_light_green_500,
        "rock" to R.color.md_grey_500,
        "ghost" to R.color.md_indigo_500,
        "dark" to R.color.md_grey_900,
        "dragon" to R.color.md_deep_purple_500,
        "steel" to R.color.md_blue_grey_500,
        "fairy" to R.color.md_pink_200
    )


    val typeNamesMap = mapOf(
        "normal" to R.string.normal,
        "electric" to R.string.electric,
        "fire" to R.string.fire,
        "water" to R.string.water,
        "grass" to R.string.grass,
        "ice" to R.string.ice,
        "fighting" to R.string.fighting,
        "poison" to R.string.poison,
        "ground" to R.string.ground,
        "flying" to R.string.flying,
        "psychic" to R.string.psychic,
        "bug" to R.string.bug,
        "rock" to R.string.rock,
        "ghost" to R.string.ghost,
        "dark" to R.string.dark,
        "dragon" to R.string.dragon,
        "steel" to R.string.steel,
        "fairy" to R.string.fairy
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = PokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // Obtener el Intent que inició esta actividad
        val intent = intent

        // Extraer datos del Intent
        val name = intent.getStringExtra("name")
        val url = intent.getStringExtra("url")
        binding.progressHp.setOnProgressChangeListener { binding.progressHp.labelText = "heart ${it.toInt()}%" }

        if (name != null) {
            pokemonInfo.onCreate(name)
        }

        pokemonInfo.pokemonInfo.observe(this, Observer {
            if (url != null) {
                updateUI(it,url)
            }
        })

        // Observar el estado de isLoading en el ViewModel
        pokemonInfo.isLoading2.observe(this, Observer { isLoading ->
            if (isLoading) {
                binding.materialCardView.visibility = View.GONE
                binding.header.visibility = View.GONE

                binding.progressbar.getIndeterminateDrawable()
                    .setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                // Mostrar ProgressBar
                binding.progressbar.visibility = View.VISIBLE
            } else {
                // Ocultar ProgressBar
                binding.progressbar.visibility = View.GONE
                binding.materialCardView.visibility = View.VISIBLE
                binding.header.visibility = View.VISIBLE
            }
        })

        binding.back.setOnClickListener { finish()}
    }

    private fun updateUI(pokemonInfo: PokemonInfo, url:String) {
        binding.name.text = pokemonInfo.name
        Glide.with(this)
            .load(pokemonInfo.getImageUrl(url))
            .centerInside()
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    // Configurar la imagen en el ImageView
                    binding.image.setImageDrawable(resource)

                    // Convertir la imagen cargada a un mapa de bits
                    val bitmap = (resource as BitmapDrawable).bitmap

                    // Extraer el color dominante de la imagen utilizando la biblioteca Palette
                    val dominantColor = Palette.from(bitmap).generate().dominantSwatch?.rgb ?: Color.TRANSPARENT

                    // Aplicar el color dominante al fondo del CardView u otros elementos de la interfaz de usuario
                    binding.header.setBackgroundColor(dominantColor)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // Se llama cuando se borra la carga de la imagen
                }
            })
        binding.weight.text = "${pokemonInfo.weight.toDouble() / 10} KG"
        binding.height.text = "${pokemonInfo.height.toDouble() / 10} M"
        binding.index.text = "#${String.format("%03d",pokemonInfo.id)}"

        pokemonInfo.stats.forEach { stat ->
            when(stat.stat.name){
                "hp"->{
                    binding.progressHp.max =  150F
                    binding.progressHp.progress += stat.base_stat.toFloat()
                    binding.progressHp.labelText = "${stat.base_stat} / ${150}"
                }
                "attack"->{
                    binding.pvatk.max = 150F
                    binding.pvatk.progress += stat.base_stat.toFloat()
                    binding.pvatk.labelText = "${stat.base_stat}/${150}"
                }
                "defense"->{
                    binding.pvdef.max = 150F
                    binding.pvdef.progress += stat.base_stat.toFloat()
                    binding.pvdef.labelText = "${stat.base_stat}/${150}"
                }
                "speed"->{
                    binding.pvspd.max = 150F
                    binding.pvspd.progress += stat.base_stat.toFloat()
                    binding.pvspd.labelText = "${stat.base_stat}/${150}"
                }
                else->{
                    binding.pvexp.max = 1000F
                    val random = Random.nextInt(1000)
                    binding.pvexp.progress =  random.toFloat()
                    binding.pvexp.labelText = "${random}/${1000}"
                }

            }
        }


        pokemonInfo.types.forEach { type ->
            val typeName = resources.getString(resources.getIdentifier(type.type.name, "string", packageName))
            val colorRes = typeColorMap[type.type.name]
            colorRes?.let { color ->
                binding.ribbonRecyclerView.addRibbon(
                    ribbonView = ribbonView(this@PokemonDetail) {
                        setText(typeName)
                        setTextColor(Color.WHITE) // Puedes personalizar el color del texto según sea necesario
                        setRibbonBackgroundColorResource(color)
                        setRibbonRadius(10f)
                        setPaddingLeft(84f)
                        setPaddingRight(84f)
                        setPaddingTop(2f)
                        setPaddingBottom(10f)
                        setTextSize(16f)
                        setRibbonRadius(120f)
                    }
                )
            }
        }



    }


}