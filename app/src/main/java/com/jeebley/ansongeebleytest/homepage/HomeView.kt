package com.jeebley.ansongeebleytest.homepage


import com.jeebley.ansongeebleytest.models.RestaurantInfo
import com.jeebley.ansongeebleytest.models.RestaurantListResponse


interface HomeView {
    fun showWait()

    fun removeWait()

    fun showLayout()

    fun hideLayout()

    fun onFailure(appErrorMessage: String)

    fun getRestaurantInfoSuccess(restaurantInfo: RestaurantInfo)

    fun getRestaurantMenListSuccess(restaurantListResponse: RestaurantListResponse)

}