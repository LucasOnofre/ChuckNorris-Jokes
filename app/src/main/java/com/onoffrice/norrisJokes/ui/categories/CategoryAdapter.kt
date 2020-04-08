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

): RecyclerView.Adapter<CategoryAdapter.JokeViewHolder>() {

    var list: MutableList<String> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): JokeViewHolder {

        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)

        return JokeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val category = list[position]

        category.let {
            holder.categoryItem.text = category.capitalize()

            holder.itemView.setOnClickListener {
                listener.onClickCategory(category)
            }
        }
    }

    class JokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryItem = itemView.categoryItem
    }
}
