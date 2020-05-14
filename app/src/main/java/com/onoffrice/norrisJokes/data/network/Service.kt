package com.onoffrice.norrisJokes.data.network

import com.onoffrice.norrisJokes.data.network.model.Joke
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("categories")
    fun getCategories(): Single<MutableList<String>>

    @GET("random")
    fun getJokeByCategory(@Query("category") category: String): Single<Joke>
}