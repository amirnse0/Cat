package com.abbas.cats.data

import com.abbas.cats.data.model.CatResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsService {

    @GET("images/search")
    suspend fun getCats(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("has_breeds") hasBreeds: Boolean = true,
    ): List<CatResponse>
}