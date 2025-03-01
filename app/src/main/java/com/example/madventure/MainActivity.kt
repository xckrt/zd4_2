package com.example.madventure

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigation = findViewById<BottomNavigationView>(R.id.navigation)

        navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_chanel1 -> {
                    startChannelActivity("Канал 1")
                    true
                }
                R.id.navigation_chanel2 -> {
                    startChannelActivity("Канал 2")
                    true
                }
                R.id.navigation_chanel3 -> {
                    startChannelActivity("Канал 3")
                    true
                }

                else -> false
            }
        }
    }
    private fun startChannelActivity(genre: String) {
        val intent = Intent(this, ChannelActivity::class.java).apply {
            putExtra("GENRE", genre)
        }
        startActivity(intent)
    }
}