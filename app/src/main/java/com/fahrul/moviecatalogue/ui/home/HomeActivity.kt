package com.fahrul.moviecatalogue.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.fahrul.moviecatalogue.R
import com.fahrul.moviecatalogue.databinding.ActivityHomeBinding
import com.fahrul.moviecatalogue.ui.notifications.NotificationActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.viewPager.adapter = sectionsPagerAdapter
        activityHomeBinding.tabs.setupWithViewPager(activityHomeBinding.viewPager)

        val toolbar = activityHomeBinding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_notification){
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}