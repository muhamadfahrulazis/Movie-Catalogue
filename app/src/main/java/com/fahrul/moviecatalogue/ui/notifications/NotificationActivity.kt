package com.fahrul.moviecatalogue.ui.notifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.fahrul.moviecatalogue.R
import com.fahrul.moviecatalogue.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val notificationBinding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(notificationBinding.root)

        val toolbar = notificationBinding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}