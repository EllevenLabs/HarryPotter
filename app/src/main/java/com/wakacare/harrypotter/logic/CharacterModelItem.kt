package com.wakacare.harrypotter.logic


data class CharacterModelItem(

    val alternate_names: List<String>? = null,
    val ancestry: String? = null,
    val dateOfBirth: String? = null,
    val eyeColour: String? = null,
    val gender: String? = null,
    val hairColour: String? = null,
    val hogwartsStaff: Boolean? = null,
    val hogwartsStudent: Boolean? = null,
    val house: String? = null,

    val image: String? = null,
    val name: String? = null,
    val patronus: String? = null,
    val species: String? = null,
    val wand: Wand? = null,
    val wizard: Boolean? = null,
    val yearOfBirth: Int? = null
)

data class Wand(
    val core: String?=null,
    val length: Double?=null,
    val wood: String?=null
)