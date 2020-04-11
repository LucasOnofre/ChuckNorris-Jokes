package com.onoffrice.norrisJokes.data.di

import com.onoffrice.norrisJokes.ui.categories.CategoriesActivity
import com.onoffrice.norrisJokes.ui.randomJoke.RandomJokeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    PresenterModule::class,
    NetworkModule::class,
    MainModule::class])

interface AppComponent {
    fun inject(target: CategoriesActivity)
    fun inject(target: RandomJokeActivity)

}