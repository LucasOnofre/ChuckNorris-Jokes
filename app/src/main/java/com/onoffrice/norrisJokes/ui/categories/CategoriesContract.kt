package com.onoffrice.norrisJokes.ui.categories

import com.onoffrice.norrisJokes.base.BasePresenter


interface CategoriesContract {
    interface View {
        fun displayLoading(loading: Boolean)
        fun displayError(message: String?)
        fun displayCategories(categoryList: MutableList<String>)
        fun openCategoryChosen(category: String)
    }

    interface Presenter : BasePresenter<View> {
        fun getCategories()
        fun categoryClicked(category: String)
    }
}