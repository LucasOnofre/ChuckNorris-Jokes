package com.onoffrice.norrisJokes.data.network

import com.onoffrice.norrisJokes.data.models.Joke
import io.reactivex.Single
import javax.inject.Inject

interface  Repository {
    fun getCategories() : Single<MutableList<String>>
    fun getJokeByCategory(category: String) : Single<Joke>
}

class RepositoryImp @Inject constructor(private val service: Service): Repository {

   override fun getCategories(): Single<MutableList<String>> {
        return service.getCategories()
    }

   override fun getJokeByCategory(category: String): Single<Joke> {
        return service.getJokeByCategory(category)
    }
}