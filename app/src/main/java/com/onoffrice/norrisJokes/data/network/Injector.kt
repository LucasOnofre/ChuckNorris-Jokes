package com.onoffrice.norrisJokes.data.network

import android.util.Log
import com.onoffrice.norrisJokes.ui.categories.CategoriesPresenter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Injector {

    const val DI_TAG = "DI_TAG"


    private fun provideHttpInterceptor() =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private fun provideAllInterceptors() =
        listOf(
            provideHttpInterceptor()
        )

    private fun provideOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val interceptors = provideAllInterceptors()

        interceptors.forEach { interceptor ->
            Log.i(DI_TAG, "Interceptor instance used when providing OkHttp: $interceptor")
            builder.addInterceptor(interceptor)
        }

        return builder.build()
    }

    private fun provideConverter() = GsonConverterFactory.create()
    private fun provideAdapter() = RxJava2CallAdapterFactory.create()

    fun provideRetrofitBuilder(): Retrofit.Builder {
        val okHttpClient = provideOkHttp()
        Log.i(DI_TAG, "OkHttpClient instance used when providing Retrofit: $okHttpClient")

        val adapter = provideAdapter()
        Log.i(DI_TAG, "RxJava2CallAdapterFactory instance used when providing Retrofit: $adapter")

        val converter = provideConverter()
        Log.i(DI_TAG, "GsonConverterFactory instance used when providing Retrofit: $converter")

        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(adapter)
            .addConverterFactory(converter)
    }

    fun provideBaseUrl() = "https://api.chucknorris.io/jokes/"

    private fun provideService(): Service {
        val url = provideBaseUrl()
        val retrofit = provideRetrofitBuilder().baseUrl(url).build()
        return ServiceGenerator(retrofit).forClass(Service::class.java)
    }

    private fun provideRepository(): Repository {
        val service = provideService()
        return RepositoryImp(service)
    }

    fun providePresenter(): CategoriesPresenter {
        val repository = provideRepository()
        return CategoriesPresenter(repository)
    }
}