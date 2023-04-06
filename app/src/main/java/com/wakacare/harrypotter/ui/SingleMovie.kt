package com.wakacare.harrypotter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wakacare.harrypotter.R
import com.wakacare.harrypotter.logic.Datafactory

class SingleMovie : AppCompatActivity() {

    //private lateinit var binding : ActivitySingleMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)

        displayCharacterDetails()
    }

    private fun displayCharacterDetails() {
        try {
            //get details from datafactory and display them
            val characterDetails = Datafactory.getCharacterDetails()



        }catch (e:java.lang.Exception){
            throw e
        }
    }
}