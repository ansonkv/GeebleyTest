package com.jeebley.ansongeebleytest

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.jeebley.ansongeebleytest.deps.DaggerDeps
import com.jeebley.ansongeebleytest.deps.Deps
import com.jeebley.ansongeebleytest.networking.NetworkModule

import java.io.File

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

open class BaseApp : AppCompatActivity() {
    lateinit var deps: Deps
        internal set

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cacheFile = File(cacheDir, "responses")
        deps = DaggerDeps.builder().networkModule(NetworkModule(cacheFile)).build()

    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}
