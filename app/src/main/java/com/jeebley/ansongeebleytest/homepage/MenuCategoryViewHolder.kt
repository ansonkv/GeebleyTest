package com.jeebley.ansongeebleytest.homepage


import android.view.View
import android.widget.TextView

import com.bignerdranch.expandablerecyclerview.ParentViewHolder
import com.jeebley.ansongeebleytest.R
import com.jeebley.ansongeebleytest.models.CategoryItem

class MenuCategoryViewHolder(itemView: View) : ParentViewHolder<*, *>(itemView) {
    private val menuCategoryTextView: TextView

    init {
        menuCategoryTextView = itemView.findViewById(R.id.tv_menu_category)
    }

    fun bind(categoryItem: CategoryItem) {
        menuCategoryTextView.text = categoryItem.menuNameEng
    }

    companion object {
        private val INITIAL_POSITION = 0.0f
        private val ROTATED_POSITION = 180f
    }

}
