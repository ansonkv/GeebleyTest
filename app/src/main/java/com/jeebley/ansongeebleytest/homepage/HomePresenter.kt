package com.jeebley.ansongeebleytest.homepage

import com.jeebley.ansongeebleytest.models.RestaurantInfo
import com.jeebley.ansongeebleytest.models.RestaurantListResponse
import com.jeebley.ansongeebleytest.networking.NetworkError
import com.jeebley.ansongeebleytest.networking.Service

import rx.Subscription
import rx.subscriptions.CompositeSubscription

class HomePresenter(private val service: Service, private val view: HomeView) {
    private val subscriptions: CompositeSubscription

    init {
        this.subscriptions = CompositeSubscription()
    }

    fun getMenuList() {
        view.showWait()

        val subscription = service.getMenuList(object : Service.GetRestaurantMenuListCallback {
            override fun onSuccess(cityListResponse: RestaurantListResponse) {
                view.removeWait()
                view.getRestaurantMenListSuccess(cityListResponse)
            }

            override fun onError(networkError: NetworkError) {
                view.removeWait()
                view.onFailure(networkError.appErrorMessage)
            }

        })

        subscriptions.add(subscription)
    }

    fun getRestaurantInfo() {
        view.showWait()

        val subscription = service.getRestaurantInfo(object : Service.GetRestaurantInfoCallback {
            override fun onSuccess(restaurantInfo: RestaurantInfo) {
                view.removeWait()
                view.getRestaurantInfoSuccess(restaurantInfo)
            }

            override fun onError(networkError: NetworkError) {
                view.removeWait()
                view.onFailure(networkError.appErrorMessage)
            }

        })

        subscriptions.add(subscription)
    }

    fun onStop() {
        subscriptions.unsubscribe()
    }

}
