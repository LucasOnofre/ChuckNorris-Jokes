package com.onoffrice.norrisJokes.ui.randomJoke

import android.content.Context
import android.os.Bundle
import com.onoffrice.norrisJokes.Constants
import com.onoffrice.norrisJokes.R
import com.onoffrice.norrisJokes.base.BaseActivity
import com.onoffrice.norrisJokes.data.network.model.Joke
import com.onoffrice.norrisJokes.utils.extensions.appComponent
import com.onoffrice.norrisJokes.utils.extensions.loadImage
import com.onoffrice.norrisJokes.utils.extensions.setVisible
import com.onoffrice.norrisJokes.utils.extensions.startBrowserWithUrlIntent
import kotlinx.android.synthetic.main.category_activity.swipeRefresh
import kotlinx.android.synthetic.main.random_joke_activity.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import javax.inject.Inject


class RandomJokeActivity : BaseActivity(), RandomJokeContract.View {

    @Inject
    lateinit var randomJokePresenter: RandomJokePresenter

    private lateinit var randomJoke: Joke
    private lateinit var selectedCategory: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.random_joke_activity)
        appComponent().inject(this)

        selectedCategory = intent.getSerializableExtra(Constants.SELECTED_CATEGORY) as String

        setToolbar(selectedCategory.capitalize(), true)
        setListeners()

        randomJokePresenter.attachView(this)
        randomJokePresenter.getJoke(selectedCategory)

    }

    private fun setListeners() {
        swipeRefresh.setOnRefreshListener {
            randomJokePresenter.getJoke(selectedCategory)
        }

        jokeSiteBtn.setOnClickListener {
            randomJokePresenter.onGoToSiteBtnClicked()
        }
    }

    override fun displayError(message: String?) {
        toast(message ?: getString(R.string.common_error))
    }

    override fun displayJoke(joke: Joke) {
        randomJoke = joke

        jokeIcon.loadImage(joke.iconUrl)
        jokeText.text = joke.value
    }

    override fun displayLoading(loading: Boolean) {
        swipeRefresh.isRefreshing = loading

        setViewsOnLoading(!loading)
    }

    private fun setViewsOnLoading(isVisible: Boolean) {
        jokeSiteBtn.setVisible(isVisible)
        jokeIcon.setVisible(isVisible)
        jokeText.setVisible(isVisible)
    }

    override fun openSite(url: String) {
        startBrowserWithUrlIntent(url)
    }
}

fun Context.createRandomJokeIntent(category: String) = intentFor<RandomJokeActivity>(Constants.SELECTED_CATEGORY to category)

