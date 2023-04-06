package com.wakacare.harrypotter.logic

//used for data persistance

object Datafactory {


    private var characterDetails: CharacterModelItem? = null

    fun setCharacterDetails(model: CharacterModelItem) {
        characterDetails = model
    }

    fun getCharacterDetails(): CharacterModelItem? {
        if (characterDetails == null) {
            characterDetails = null
        }
        return characterDetails
    }
}