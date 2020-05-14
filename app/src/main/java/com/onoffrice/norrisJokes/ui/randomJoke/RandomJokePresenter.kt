package com.onoffrice.norrisJokes.ui.randomJoke

import com.onoffrice.norrisJokes.data.network.Repository
import com.onoffrice.norrisJokes.data.network.model.Joke
import com.onoffrice.norrisJokes.utils.extensions.singleSubscribe
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RandomJokePresenter @Inject constructor(private val repository: Repository) : RandomJokeContract.Presenter {

    private var view: RandomJokeContract.View? = null
    private  var disposable = CompositeDisposable()

    private lateinit var joke: Joke

    override fun attachView(mvpView: RandomJokeContract.View) {
        view = mvpView
    }
    override fun detachView() {
        view = null
        disposable.dispose()
    }

    override fun getJoke(category: String) {
        disposable.add(repository.getJokeByCategory(category).singleSubscribe(
            onLoading = {
                view?.displayLoading(it)
            },
            onSuccess = {
                joke = it
                view?.displayJoke(joke)
            },
            onError = {
                view?.displayError(it.message)
            }
        ))
    }

    override fun onGoToSiteBtnClicked() {
        view?.openSite(joke.url)
    }
}