package com.wakacare.harrypotter.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.wakacare.harrypotter.R
import com.wakacare.harrypotter.databinding.FragmentHPCharacterBinding
import com.wakacare.harrypotter.logic.CharacterModelItem
import com.wakacare.harrypotter.logic.Wand
import com.wakacare.harrypotter.logic.Webservices

import com.wakacare.harrypotter.ui.adapter.HpCharacterAdapter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HPCharacterFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentHPCharacterBinding

    private var characterList: List<CharacterModelItem> = emptyList()

    private var adapter: HpCharacterAdapter? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_h_p_character, container, false)

        //initialize recyclerview
        setRecyclerView()

        //set up search view
        binding.searchView.setOnQueryTextListener(this)

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setRecyclerView() {
        try {
            val recyclerView = binding.charactersRv
            recyclerView.layoutManager = LinearLayoutManager(requireActivity())
            val adapter = HpCharacterAdapter(requireContext(), emptyList())
            recyclerView.adapter = adapter

            // create a Retrofit instance
            val retrofit = Retrofit.Builder()
                .baseUrl("https://hp-api.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val apiService = retrofit.create(Webservices::class.java)

            // call the API using a coroutine
            lifecycleScope.launch {
                try {
                    val characters = apiService.getCharacters()
                    adapter.itemList = characters
                    adapter.notifyDataSetChanged()
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        } catch (e: Exception) {
            throw e
        }
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
//not needed, search happens in real-time as user types
        return false
    }

    fun updateList(filteredList: List<CharacterModelItem>) {
        // update the characterList with the filteredList
        characterList = filteredList

        // notify the adapter
        (binding.charactersRv.adapter as? HpCharacterAdapter)?.submitList(characterList)
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        //filter the list based on the search query
        val filteredList = characterList.filter { character ->
            character.name!!.contains(newText.toString(), ignoreCase = true)
        }
        //update the adapter with the filtered list
        updateList(filteredList)
        return true
    }

}