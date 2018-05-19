package com.jeebley.ansongeebleytest.homepage

import android.content.Intent
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView


import com.jeebley.ansongeebleytest.BaseApp
import com.jeebley.ansongeebleytest.R
import com.jeebley.ansongeebleytest.menudetailspage.MenuDetailsActivity
import com.jeebley.ansongeebleytest.models.MenuItemInfo
import com.jeebley.ansongeebleytest.models.RestaurantInfo
import com.jeebley.ansongeebleytest.models.RestaurantListResponse
import com.jeebley.ansongeebleytest.networking.Service

import javax.inject.Inject

class HomePageActivity : BaseApp(), HomeView {
    private var androidToolbar: Toolbar? = null
    private var mCollapsingToolbarLayout: CollapsingToolbarLayout? = null
    private val mRootLayout: CoordinatorLayout? = null
    private var imageViewToolBar: ImageView? = null
    private var recyclerView: RecyclerView? = null
    private var textViewTime: TextView? = null
    private var linearLayoutContainer: LinearLayout? = null
    @Inject
    var service: Service? = null
    internal lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deps.inject(this)

        renderView()

        val presenter = service?.let { HomePresenter(it, this) }
        presenter!!.getRestaurantInfo()
        presenter.getMenuList()
    }

    private fun renderView() {
        setContentView(R.layout.activity_main)
        //   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        androidToolbar = findViewById<View>(R.id.toolbar_android) as Toolbar
        setSupportActionBar(androidToolbar)

        supportActionBar!!.setHomeButtonEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        mCollapsingToolbarLayout = findViewById<View>(R.id.collapsingToolbarLayoutAndroidExample) as CollapsingToolbarLayout
        mCollapsingToolbarLayout!!.title = ""
        linearLayoutContainer = findViewById(R.id.ll_container)
        progressBar = findViewById(R.id.progress)
        recyclerView = findViewById(R.id.list)
        imageViewToolBar = findViewById(R.id.parallax_header_imageview)
        textViewTime = findViewById(R.id.tv_restaurant_time)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
    }


    override fun showWait() {
        progressBar.visibility = View.VISIBLE
    }

    override fun removeWait() {
        progressBar.visibility = View.GONE
    }

    override fun showLayout() {
        linearLayoutContainer!!.visibility = View.VISIBLE
    }

    override fun hideLayout() {
        linearLayoutContainer!!.visibility = View.GONE
    }

    override fun onFailure(appErrorMessage: String) {

    }

    override fun getRestaurantInfoSuccess(restaurantInfo: RestaurantInfo) {

        /**restaurant image url doesn't have data */
        //        Glide.with(this)
        //                .load(restaurantInfo.getRestaurantAreaInfo().getRImage())
        //                .into(imageViewToolBar)
        //                .onLoadFailed(getDrawable(R.drawable.image_place_holder));
        textViewTime!!.text = restaurantInfo.restaurantAreaInfo!!.workingHour
        mCollapsingToolbarLayout!!.title = restaurantInfo.restaurantAreaInfo!!.rName
    }

    override fun getRestaurantMenListSuccess(restaurantListResponse: RestaurantListResponse) {

        var itemAdapter = MenuItemAdapter(applicationContext,
                restaurantListResponse.categoryArray!!,
                object : MenuItemAdapter.OnRestMenuItemClickListener {
                    override fun onClick(menuItem: MenuItemInfo) {
                        val intent =MenuDetailsActivity.newIntent(applicationContext,menuItem)
                        startActivity(intent)
                        overridePendingTransition(R.anim.detail_in, R.anim.activity_out)
                    }
                })
        recyclerView!!.adapter = itemAdapter
        showLayout()
    }
}
