package com.example.punk.data.api

import com.example.punk.entity.BeerDetailsResponse
import com.example.punk.entity.BeerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PunkApi {

    @GET("beers")
    suspend fun getBeers(
        @Query("page") pageNumber: Int,
        @Query("per_page") pageSize: Int
    ): Response<List<BeerResponse>>

    @GET("beers/{beer_id}")
    suspend fun getBeerDetails(
        @Path("beer_id") beerId: Int
    ): Response<List<BeerDetailsResponse>>
}
