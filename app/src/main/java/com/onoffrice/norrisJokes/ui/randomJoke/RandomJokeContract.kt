package com.onoffrice.norrisJokes.ui.randomJoke

import com.onoffrice.norrisJokes.base.BasePresenter
import com.onoffrice.norrisJokes.data.network.model.Joke


interface RandomJokeContract {
    interface View {
        fun displayLoading(loading: Boolean)
        fun displayError(message: String?)
        fun displayJoke(joke: Joke)
        fun openSite(url: String)
    }

    interface Presenter : BasePresenter<View> {
        fun getJoke(category: String)
        fun onGoToSiteBtnClicked()
    }
}