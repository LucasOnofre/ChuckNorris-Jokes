package com.onoffrice.norrisJokes

import androidx.multidex.MultiDexApplication
import com.onoffrice.norrisJokes.data.di.AppComponent
import com.onoffrice.norrisJokes.data.di.AppModule
import com.onoffrice.norrisJokes.data.di.DaggerAppComponent

class BaseApplication : MultiDexApplication() {

    private lateinit var appComponent: AppComponent
    fun appComp() = appComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    private fun initDagger(app: BaseApplication): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}
