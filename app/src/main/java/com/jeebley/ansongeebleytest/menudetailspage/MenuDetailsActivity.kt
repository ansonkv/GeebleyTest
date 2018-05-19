package com.jeebley.ansongeebleytest.menudetailspage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.bumptech.glide.Glide
import com.jeebley.ansongeebleytest.BaseApp
import com.jeebley.ansongeebleytest.R
import com.jeebley.ansongeebleytest.models.MenuItemInfo

class MenuDetailsActivity : BaseApp() {
    private var androidToolbar: Toolbar? = null
    private var mCollapsingToolbarLayout: CollapsingToolbarLayout? = null
    private var imageViewItem: ImageView? = null
    private var textViewItemDescription: TextView? = null
    private var textViewPlus: TextView? = null
    private var textViewMinus: TextView? = null
    private var textViewCount: TextView? = null
    private var buttonAddCart: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deps.inject(this)
        val intent = intent
        val menuItemInfo = intent.getSerializableExtra(INTENT_MENU_ITEM_INFO) as MenuItemInfo
        renderView()
        loadValues(menuItemInfo)
    }

    private fun renderView() {
        setContentView(R.layout.activity_menu_item_details)

        androidToolbar = findViewById<View>(R.id.toolbar_android) as Toolbar
        androidToolbar!!.setNavigationIcon(R.drawable.ic_close)
        setSupportActionBar(androidToolbar)

        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mCollapsingToolbarLayout = findViewById<View>(R.id.collapsingToolbarLayoutAndroidExample) as CollapsingToolbarLayout

        mCollapsingToolbarLayout!!.title = ""
        imageViewItem = findViewById(R.id.parallax_header_imageview)
        textViewItemDescription = findViewById(R.id.tv_item_desc)
        textViewPlus = findViewById(R.id.count_plus)
        textViewMinus = findViewById(R.id.count_minus)
        textViewCount = findViewById(R.id.tv_order_count)
        buttonAddCart = findViewById(R.id.btn_add_to_cart)

    }


    private fun loadValues(menuItemInfo: MenuItemInfo) {
        mCollapsingToolbarLayout!!.title = menuItemInfo.itemNameEng
        Glide.with(this).load(menuItemInfo.itemImage).into(imageViewItem!!)
        textViewItemDescription!!.text = menuItemInfo.itemDescEng
        textViewCount!!.text = menuItemInfo.itemMinQty
        textViewPlus!!.setOnClickListener {
            var count = Integer.parseInt(textViewCount!!.text as String)
            count++
            textViewCount!!.text = count.toString() + ""
        }
        textViewMinus!!.setOnClickListener {
            var count = Integer.parseInt(textViewCount!!.text as String)
            if (count <= Integer.parseInt(menuItemInfo.itemMinQty as String)) {

            } else {
                count--
            }
            textViewCount!!.text = count.toString() + ""
        }
        buttonAddCart!!.setOnClickListener {
            val message = "Item ordered with Menu Item id : " + menuItemInfo.itemId + "\n and count is: " + textViewCount!!.text + ""
            Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                super.onBackPressed()
                overridePendingTransition(R.anim.detail_fade, R.anim.activity_in)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.detail_fade, R.anim.activity_in)
    }

    companion object {
        private val INTENT_MENU_ITEM_INFO = "MENU_ITEM"
        fun newIntent(context: Context, menuItemInfo: MenuItemInfo): Intent {
            val intent = Intent(context, MenuDetailsActivity::class.java)
            intent.putExtra(INTENT_MENU_ITEM_INFO, menuItemInfo)
            return intent
        }
    }
}
