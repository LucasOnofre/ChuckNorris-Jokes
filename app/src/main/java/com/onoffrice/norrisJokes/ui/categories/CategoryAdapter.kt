package com.onoffrice.norrisJokes.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onoffrice.norrisJokes.R
import kotlinx.android.synthetic.main.category_item.view.*

interface CategoryClickListener {
   fun onClickCategory(category: String)
}

class CategoryAdapter (
    private val listener: CategoryClickListener

): RecyclerView.Adapter<CategoryAdapter.JokeCategoryViewHolder>() {

    var list: MutableList<String> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): JokeCategoryViewHolder {

        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)

        return JokeCategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: JokeCategoryViewHolder, position: Int) {
        val category = list[position]

        category.let {
            holder.categoryItem.text = category.capitalize()

            holder.itemView.setOnClickListener {
                listener.onClickCategory(category)
            }
        }
    }

    class JokeCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryItem = itemView.categoryItem
    }
}
