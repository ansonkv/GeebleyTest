package com.jeebley.ansongeebleytest.homepage

import android.content.Context
import android.support.annotation.UiThread
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter
import com.jeebley.ansongeebleytest.R
import com.jeebley.ansongeebleytest.models.CategoryItem
import com.jeebley.ansongeebleytest.models.MenuItemInfo

class MenuItemAdapter(private val context: Context, categoryItemList: List<CategoryItem>, private val listener: OnRestMenuItemClickListener) : ExpandableRecyclerAdapter<CategoryItem, MenuItemInfo, MenuCategoryViewHolder, MenuItemViewHolder>(categoryItemList) {
    private val mInflater: LayoutInflater
    private val categoryItemList: List<CategoryItem>? = null

    init {
        var categoryItemList = categoryItemList
        categoryItemList = categoryItemList
        mInflater = LayoutInflater.from(context)
    }

    @UiThread
    override fun onCreateParentViewHolder(parentViewGroup: ViewGroup, viewType: Int): MenuCategoryViewHolder {
        val categoryView: View
        categoryView = mInflater.inflate(R.layout.list_single_item_parent, parentViewGroup, false)
        return MenuCategoryViewHolder(categoryView)
    }

    @UiThread
    override fun onCreateChildViewHolder(childViewGroup: ViewGroup, viewType: Int): MenuItemViewHolder {
        val menuItemView: View
        menuItemView = mInflater.inflate(R.layout.list_item_child, childViewGroup, false)
        return MenuItemViewHolder(menuItemView)
    }

    @UiThread
    override fun onBindParentViewHolder(menuCategoryViewHolder: MenuCategoryViewHolder, parentPosition: Int, categoryItem: CategoryItem) {
        menuCategoryViewHolder.bind(categoryItem)
    }

    @UiThread
    override fun onBindChildViewHolder(menuItemViewHolder: MenuItemViewHolder, parentPosition: Int, childPosition: Int, menuItemInfo: MenuItemInfo) {
        menuItemViewHolder.bind(menuItemInfo, context)
        menuItemViewHolder.click(menuItemInfo, listener)
    }

    interface OnRestMenuItemClickListener {
        fun onClick(menuItemInfo: MenuItemInfo)
    }


}
