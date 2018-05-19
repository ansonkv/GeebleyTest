package com.jeebley.ansongeebleytest.networking

import com.jeebley.ansongeebleytest.models.RestaurantInfo
import com.jeebley.ansongeebleytest.models.RestaurantListResponse

import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers


class Service(private val networkService: NetworkService) {

    fun getMenuList(callback: GetRestaurantMenuListCallback): Subscription {

        return networkService.restaurantMenuList
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext { throwable -> Observable.error(throwable) }
                .subscribe(object : Subscriber<RestaurantListResponse>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        callback.onError(NetworkError(e))

                    }

                    override fun onNext(restaurantListResponse: RestaurantListResponse) {
                        callback.onSuccess(restaurantListResponse)

                    }
                })
    }

    interface GetRestaurantMenuListCallback {
        fun onSuccess(cityListResponse: RestaurantListResponse)

        fun onError(networkError: NetworkError)
    }

    fun getRestaurantInfo(callback: GetRestaurantInfoCallback): Subscription {

        return networkService.restaurantInfo
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext { throwable -> Observable.error(throwable) }
                .subscribe(object : Subscriber<RestaurantInfo>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        callback.onError(NetworkError(e))

                    }

                    override fun onNext(restaurantInfo: RestaurantInfo) {
                        callback.onSuccess(restaurantInfo)

                    }
                })
    }

    interface GetRestaurantInfoCallback {
        fun onSuccess(restaurantInfo: RestaurantInfo)

        fun onError(networkError: NetworkError)
    }
}