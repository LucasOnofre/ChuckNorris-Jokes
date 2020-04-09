package com.onoffrice.norrisJokes.data.di

import com.onoffrice.norrisJokes.data.di.NetworkModule.Companion.BASE_URL
import com.onoffrice.norrisJokes.data.network.Service
import com.onoffrice.norrisJokes.data.network.ServiceGenerator
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit.Builder, @Named(BASE_URL) url: String): Service {
        return ServiceGenerator(retrofit.baseUrl(url).build()).forClass(Service::class.java)
    }
}