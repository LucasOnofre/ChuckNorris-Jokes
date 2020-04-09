package com.onoffrice.norrisJokes.data.di

import android.content.Context
import com.onoffrice.norrisJokes.data.network.interceptors.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        const val BASE_URL = "BASE_URL"
    }

    @Provides
    @Named(BASE_URL)
    fun provideBaseUrlString() = "https://api.chucknorris.io/jokes/"

    @Provides
    @Singleton
    @IntoSet
    fun provideHttpInterceptor(): Interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    @IntoSet
    fun provideNetworkInterceptor(context: Context):Interceptor = NetworkInterceptor(context)

    @Provides
    @Singleton
    @ElementsIntoSet
    fun provideInterceptors(): Set<Interceptor> = setOf()

    @Provides
    @Singleton
    fun provideOkHttp(interceptors: @JvmSuppressWildcards(true) Set<Interceptor>): OkHttpClient {
        val builder = OkHttpClient.Builder()

        interceptors.forEach { interceptor ->
            builder.addInterceptor(interceptor)
        }

        return builder.build()
    }
    @Provides
    @Singleton
    fun provideConverter() = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideAdapter() = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(client: OkHttpClient, adapter: RxJava2CallAdapterFactory, converter: GsonConverterFactory): Retrofit.Builder {
        return Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(adapter)
            .addConverterFactory(converter)
    }
}