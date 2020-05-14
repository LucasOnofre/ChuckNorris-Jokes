package com.onoffrice.norrisJokes.ui.categories

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.onoffrice.norrisJokes.RxImmediateSchedulerRule
import com.onoffrice.norrisJokes.data.network.Repository
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CategoriesPresenterTest {

    companion object {
         val categories = mutableListOf("Categoria 1", "Categoria 2", "Categoria 3")
    }

    @Rule @JvmField var testSchedulersRule = RxImmediateSchedulerRule()

    private lateinit var view      : CategoriesContract.View
    private lateinit var presenter : CategoriesPresenter
    private lateinit var repository: Repository

    @Before
    fun setup() {
        view       = mock()
        repository = mock()
        presenter  = CategoriesPresenter(repository)

        presenter.attachView(view)
    }

    @Test
    fun test_getCategories_success_displayItemsOnView() {
        //Arrange
        Mockito.`when`(repository.getCategories()).thenReturn(Single.just(categories))

        //Act
        presenter.getCategories()

        //Assert
        verify(view).displayCategories(categories)
    }

    @Test
    fun test_getCategories_error_displayError() {
        val errorMessage = "Unknown error"
        //Arrange

        Mockito.`when`(repository.getCategories()).thenReturn(Single.error(Throwable(errorMessage)))

        //Act
        presenter.getCategories()

        //Assert
        verify(view).displayError(errorMessage)
    }
}