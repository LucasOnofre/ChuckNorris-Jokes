package com.onoffrice.norrisJokes.data.di

import com.onoffrice.norrisJokes.data.network.Repository
import com.onoffrice.norrisJokes.data.network.RepositoryImp
import com.onoffrice.norrisJokes.data.network.Service
import com.onoffrice.norrisJokes.ui.categories.CategoriesPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    @Singleton
    fun provideCategoriesPresenter(repository: Repository) =  CategoriesPresenter(repository)

    @Provides
    @Singleton
    fun provideRepository(service: Service): Repository = RepositoryImp(service)

}
