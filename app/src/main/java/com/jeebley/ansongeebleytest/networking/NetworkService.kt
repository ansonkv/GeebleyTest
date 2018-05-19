package com.jeebley.ansongeebleytest.networking

import com.jeebley.ansongeebleytest.models.RestaurantInfo
import com.jeebley.ansongeebleytest.models.RestaurantListResponse

import retrofit2.http.GET
import rx.Observable

interface NetworkService {

    @get:GET(value = "services.php?action=menuCategories&rId=366&cuisineType=1&countryId=21&langId=1")
    val restaurantMenuList: Observable<RestaurantListResponse>

    @get:GET(value = "services.php?action=restaurantAreaInfo&langId=1&countryId=21&areaId=1&rId=366")
    val restaurantInfo: Observable<RestaurantInfo>

}
