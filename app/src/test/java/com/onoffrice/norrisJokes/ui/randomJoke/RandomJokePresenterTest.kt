package com.onoffrice.norrisJokes.ui.randomJoke

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.onoffrice.norrisJokes.RxImmediateSchedulerRule
import com.onoffrice.norrisJokes.data.network.Repository
import com.onoffrice.norrisJokes.data.network.model.Joke
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RandomJokePresenterTest {

         private val joke =
            Joke(id = "123",
                url = "testeUrl.com",
                iconUrl = "testeIcone.com",
                categories = listOf("Categoria 1", "Categoria 2"),
                createdAt = "",
                updatedAt = "",
                value = "Piada" )

         private val categorie = "Categoria 1"

         private val errorMessage = "Unknown error"

    @Rule @JvmField var testSchedulersRule = RxImmediateSchedulerRule()

    private lateinit var view      : RandomJokeContract.View
    private lateinit var presenter : RandomJokePresenter
    private lateinit var repository: Repository

    @Before
    fun setup() {
        view       = mock()
        repository = mock()
        presenter  = RandomJokePresenter(repository)

        presenter.attachView(view)
    }

    @Test
    fun test_getRandomJoke_success_displayJoke() {

        //Arrange
        Mockito.`when`(repository.getJokeByCategory(categorie)).thenReturn(Single.just(joke))

        //Act
        presenter.getJoke(categorie)

        //Assert
        verify(view).displayJoke(joke)
    }

    @Test
    fun test_getRandomJoke_error_displayError() {
        //Arrange
        Mockito.`when`(repository.getJokeByCategory(categorie)).thenReturn(Single.error(Throwable(errorMessage)))

        //Act
        presenter.getJoke(categorie)

        //Assert
        verify(view).displayError(errorMessage)
    }

    @Test
    fun test_getRandomJoke_success_displayJoke_btnClicked() {

        //Arrange
        Mockito.`when`(repository.getJokeByCategory(categorie)).thenReturn(Single.just(joke))

        presenter.getJoke(categorie)

        //Act
        presenter.onGoToSiteBtnClicked()

        //Assert
        verify(view).openSite(joke.url)
    }
}