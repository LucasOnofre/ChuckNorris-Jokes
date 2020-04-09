package com.onoffrice.norrisJokes.ui.categories

import com.onoffrice.norrisJokes.data.network.Repository
import com.onoffrice.norrisJokes.utils.extensions.singleSubscribe
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CategoriesPresenter @Inject constructor(private val repository: Repository) : CategoriesContract.Presenter {

    private var view: CategoriesContract.View? = null

    private  var disposable = CompositeDisposable()

    override fun attachView(mvpView: CategoriesContract.View) {
        view = mvpView
    }

    override fun getCategories() {
        disposable.add(repository.getCategories().singleSubscribe(
            onLoading = {
                view?.displayLoading(it)
            },
            onSuccess = {
                view?.displayCategories(it)
            },

            onError = {
                view?.displayError(it.message)
            }
        ))
    }

    override fun detachView() {
        view = null
        disposable.dispose()
    }
}