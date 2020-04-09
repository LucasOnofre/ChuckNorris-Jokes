package com.onoffrice.norrisJokes.ui.categories

import android.content.Context
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.onoffrice.norrisJokes.R
import com.onoffrice.norrisJokes.base.BaseActivity
import com.onoffrice.norrisJokes.utils.extensions.appComponent
import kotlinx.android.synthetic.main.category_activity.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import javax.inject.Inject


class CategoriesActivity : BaseActivity(), CategoriesContract.View {

    @Inject
    lateinit var categoriesPresenter: CategoriesPresenter

    private val categoryAdapter: CategoryAdapter by lazy {
        val adapter = CategoryAdapter(object : CategoryClickListener{
            override fun onClickCategory(category: String) {
               toast(category)
            }
        })

        /** Add's divider in recycler view **/
        val divider = DividerItemDecoration(categoriesRv.context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(this, R.drawable.divider)?.let { divider.setDrawable(it) }
        categoriesRv.addItemDecoration(divider)

        val layoutManager          = LinearLayoutManager(this)
        categoriesRv.layoutManager = layoutManager
        categoriesRv.adapter       = adapter
        adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_activity)
        appComponent().inject(this)
        setToolbar(getString(R.string.categories_title))
        setListeners()
        categoriesPresenter.attachView(this)
        categoriesPresenter.getCategories()
    }

    private fun setListeners() {
        swipeRefresh.setOnRefreshListener {
            categoriesPresenter.getCategories()
        }
    }

    override fun displayError(message: String?) {
        toast(message ?: getString(R.string.common_error))
    }

    override fun displayLoading(loading: Boolean) {
        swipeRefresh.isRefreshing = loading
    }

    override fun displayCategories(categoryList: MutableList<String>) {
        categoryAdapter.list = categoryList
    }
}

fun Context.createCategoriesIntent() = intentFor<CategoriesActivity>()

