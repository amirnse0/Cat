package com.abbas.cats.data.model

import com.google.gson.annotations.SerializedName as SI

data class Breed(
    @SI("adaptability")
    val adaptability: Int,
    @SI("affection_level")
    val affectionLevel: Int,
    @SI("alt_names")
    val altNames: String,
    @SI("child_friendly")
    val childFriendly: Int,
    @SI("country_code")
    val countryCode: String,
    @SI("country_codes")
    val countryCodes: String,
    @SI("description")
    val description: String,
    @SI("dog_friendly")
    val dogFriendly: Int,
    @SI("energy_level")
    val energyLevel: Int,
    @SI("experimental")
    val experimental: Int,
    @SI("grooming")
    val grooming: Int,
    @SI("hairless")
    val hairless: Int,
    @SI("health_issues")
    val healthIssues: Int,
    @SI("hypoallergenic")
    val hypoallergenic: Int,
    @SI("id")
    val id: String,
    @SI("indoor")
    val indoor: Int,
    @SI("intelligence")
    val intelligence: Int,
    @SI("lap")
    val lap: Int,
    @SI("life_span")
    val lifeSpan: String,
    @SI("name")
    val name: String,
    @SI("natural")
    val natural: Int,
    @SI("origin")
    val origin: String,
    @SI("rare")
    val rare: Int,
    @SI("reference_image_id")
    val referenceImageId: String,
    @SI("rex")
    val rex: Int,
    @SI("shedding_level")
    val sheddingLevel: Int,
    @SI("short_legs")
    val shortLegs: Int,
    @SI("social_needs")
    val socialNeeds: Int,
    @SI("stranger_friendly")
    val strangerFriendly: Int,
    @SI("suppressed_tail")
    val suppressedTail: Int,
    @SI("temperament")
    val temperament: String,
    @SI("vocalisation")
    val vocalisation: Int,
    @SI("weight")
    val weight: Weight,
    @SI("wikipedia_url")
    val wikipediaUrl: String
)