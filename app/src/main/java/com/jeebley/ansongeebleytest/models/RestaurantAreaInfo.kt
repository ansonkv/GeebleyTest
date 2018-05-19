package com.jeebley.ansongeebleytest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RestaurantAreaInfo {

    @SerializedName("rId")
    @Expose
    var rId: String? = null
    @SerializedName("rInfoId")
    @Expose
    var rInfoId: String? = null
    @SerializedName("rImage")
    @Expose
    var rImage: String? = null
    @SerializedName("rName")
    @Expose
    var rName: String? = null
    @SerializedName("workingHour")
    @Expose
    var workingHour: String? = null
    @SerializedName("cntrCurrency")
    @Expose
    var cntrCurrency: String? = null
    @SerializedName("orgImage")
    @Expose
    var orgImage: String? = null

}
