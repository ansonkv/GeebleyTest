package com.jeebley.ansongeebleytest.homepage

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.bignerdranch.expandablerecyclerview.ChildViewHolder
import com.bumptech.glide.Glide
import com.jeebley.ansongeebleytest.R
import com.jeebley.ansongeebleytest.models.MenuItemInfo

class MenuItemViewHolder(itemView: View) : ChildViewHolder<Any>(itemView) {
    private val textViewMenuItemName: TextView
    private val textViewMenuItemPrice: TextView
    private val textViewItemDescription: TextView
    private val imageView: ImageView

    init {
        imageView = itemView.findViewById(R.id.img_item)
        textViewMenuItemName = itemView.findViewById(R.id.tv_item_name)
        textViewMenuItemPrice = itemView.findViewById(R.id.tv_item_price)
        textViewItemDescription = itemView.findViewById(R.id.tv_item_desc)
    }

    fun bind(menuItemInfo: MenuItemInfo, context: Context) {
        textViewMenuItemName.text = menuItemInfo.itemNameEng
        textViewMenuItemPrice.text = menuItemInfo.itemPrice!! + " KD"
        textViewItemDescription.text = menuItemInfo.itemDescEng
        Log.e("Description", menuItemInfo.itemDescEng)
        Glide.with(context)
                .load(menuItemInfo.itemImage)
                .into(imageView)
    }

    fun click(menuItemInfo: MenuItemInfo, listener: MenuItemAdapter.OnRestMenuItemClickListener) {
        itemView.setOnClickListener { listener.onClick(menuItemInfo) }
    }
}
