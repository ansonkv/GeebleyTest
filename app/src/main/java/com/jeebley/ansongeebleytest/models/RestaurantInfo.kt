package com.jeebley.ansongeebleytest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RestaurantInfo {

    @SerializedName("response_code")
    @Expose
    var responseCode: Int? = null
    @SerializedName("restaurantAreaInfo")
    @Expose
    var restaurantAreaInfo: RestaurantAreaInfo? = null

}