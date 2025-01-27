package com.abbas.cats.usecase.converter

import com.abbas.cats.data.remote.model.CatResponse
import com.abbas.cats.usecase.presentationmodel.Cat


object CatConverter {
    fun convertPresentationCat(catResponse: CatResponse): Cat = catResponse.let {
        Cat(
            id = it.id,
            name = it.breeds.first().name,
            description = it.breeds.first().description,
            origin = it.breeds.first().origin,
            temperament = it.breeds.first().temperament,
            lifeSpan = it.breeds.first().lifeSpan,
            image = it.url
        )
    }
}