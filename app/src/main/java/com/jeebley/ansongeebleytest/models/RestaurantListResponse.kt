package com.jeebley.ansongeebleytest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RestaurantListResponse {

    @SerializedName("categoryArray")
    @Expose
    var categoryArray: List<CategoryItem>? = null

}