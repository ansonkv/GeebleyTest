package com.jeebley.ansongeebleytest.models

import com.bignerdranch.expandablerecyclerview.model.Parent
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CategoryItem : Parent<MenuItemInfo> {

    @SerializedName("menuName_eng")
    @Expose
    var menuNameEng: String? = null
    @SerializedName("menuCatId")
    @Expose
    var menuCatId: String? = null
    @SerializedName("mastMId")
    @Expose
    var mastMId: String? = null
    @SerializedName("isOffer")
    @Expose
    var isOffer: Boolean? = null
    @SerializedName("menuArray")
    @Expose
    var menuArray: List<MenuItemInfo>? = null

    override fun getChildList(): List<MenuItemInfo>? {
        return menuArray
    }

    override fun isInitiallyExpanded(): Boolean {
        return false
    }
}
