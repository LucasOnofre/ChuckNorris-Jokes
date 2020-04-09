package com.onoffrice.norrisJokes.data.network.interceptors

import android.content.Context
import com.onoffrice.norrisJokes.Constants
import com.onoffrice.norrisJokes.utils.extensions.isNetworkConnected
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class NetworkInterceptor @Inject constructor(val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        if (!context.isNetworkConnected()) {
            throw NoConnectivityException()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}

class NoConnectivityException : IOException() {
    override val message: String
        get() = Constants.NETWORK_ERROR
}