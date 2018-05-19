package com.jeebley.ansongeebleytest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

class MenuItemInfo : Serializable {

    @SerializedName("itemId")
    @Expose
    var itemId: String? = null
    @SerializedName("itemResId")
    @Expose
    var itemResId: String? = null
    @SerializedName("itemName_eng")
    @Expose
    var itemNameEng: String? = null
    @SerializedName("itemDesc_eng")
    @Expose
    var itemDescEng: String? = null
    @SerializedName("itemMinQty")
    @Expose
    var itemMinQty: String? = null
    @SerializedName("itemImage")
    @Expose
    var itemImage: String? = null
    @SerializedName("isType")
    @Expose
    var isType: String? = null
    @SerializedName("itemPrice")
    @Expose
    var itemPrice: String? = null

}