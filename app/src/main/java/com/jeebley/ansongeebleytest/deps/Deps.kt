package com.jeebley.ansongeebleytest.deps

import com.jeebley.ansongeebleytest.homepage.HomePageActivity
import com.jeebley.ansongeebleytest.menudetailspage.MenuDetailsActivity
import com.jeebley.ansongeebleytest.networking.NetworkModule

import javax.inject.Singleton

import dagger.Component


@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface Deps {
    fun inject(homeActivity: HomePageActivity)
    fun inject(menuDetailsActivity: MenuDetailsActivity)
}