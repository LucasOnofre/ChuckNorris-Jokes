package com.onoffrice.norrisJokes.base

interface BasePresenter<V> {

    fun attachView(view: V)

    fun detachView()
}