package com.example.pruebakotlin

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebakotlin.data.model.PokemonListModel
import com.example.pruebakotlin.databinding.ActivityHomeBinding
import com.example.pruebakotlin.ui.adapter.OnItemClickListener
import com.example.pruebakotlin.ui.adapter.PokemonListAdapter
import com.example.pruebakotlin.ui.view.PokemonDetail
import com.example.pruebakotlin.ui.viewmodel.PokemonListNavigator
import com.example.pruebakotlin.ui.viewmodel.PokemonListViewModel
import com.google.firebase.auth.FirebaseAuth


class Home : AppCompatActivity(),PokemonListNavigator {

    lateinit var binding: ActivityHomeBinding
    private val pokemonViewModel: PokemonListViewModel by viewModels()

    private val pokemonList = mutableListOf<PokemonListModel>()
    private val pokemonListAdapter = PokemonListAdapter(object : OnItemClickListener {
        override fun onItemClick(pokemon: PokemonListModel) {
            // Acciones a realizar cuando se hace clic en un ítem
            navigateToPokemonDetail(pokemon)

        }
    })




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el RecyclerView
        binding.listPokemon.layoutManager = GridLayoutManager(this, 2)
        binding.listPokemon.adapter = pokemonListAdapter

        // Observar los cambios en el ViewModel
        pokemonViewModel.onCreate(0, 20)
        pokemonViewModel.pokemonListViewModel.observe(this, Observer { pokemonResponse ->
            pokemonListAdapter.submitList(pokemonResponse)
        })


        // Agregar el OnScrollListener al RecyclerView
        binding.listPokemon.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!pokemonViewModel.isLoading && !pokemonViewModel.isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                        pokemonViewModel.loadMorePokemon()
                    }
                }
            }
        })

        // Observar el estado de isLoading en el ViewModel
        pokemonViewModel.isLoading2.observe(this, Observer { isLoading ->
            if (isLoading) {
                // Mostrar ProgressBar
                binding.progressbar.getIndeterminateDrawable()
                    .setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);

                binding.progressbar.visibility = View.VISIBLE
            } else {
                // Ocultar ProgressBar
                binding.progressbar.visibility = View.GONE
            }
        })

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home,menu)

        val searchItem = menu?.findItem(R.id.app_bar_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                pokemonListAdapter.filter.filter(newText)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout->{
                FirebaseAuth.getInstance().signOut()
                onBackPressedDispatcher.onBackPressed()
            }


        }
        return super.onOptionsItemSelected(item)
    }

    override fun navigateToPokemonDetail(pokemon: PokemonListModel) {
        // Cuando se haga clic en un elemento de la lista, se iniciará la actividad de detalles del Pokémon
        val intent = Intent(this, PokemonDetail::class.java)
        intent.putExtra("name", pokemon.name)
        intent.putExtra("url", pokemon.url)
        startActivity(intent)
    }
}