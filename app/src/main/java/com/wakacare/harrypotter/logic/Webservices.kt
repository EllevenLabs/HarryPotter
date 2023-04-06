package com.wakacare.harrypotter.logic


import retrofit2.http.GET



interface Webservices {

    @GET("api/characters")
    suspend fun getCharacters(): List<CharacterModelItem>
}