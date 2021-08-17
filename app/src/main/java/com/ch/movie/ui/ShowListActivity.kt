package com.ch.movie.ui

import android.os.Bundle
import android.transition.Visibility
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ch.movie.R
import com.ch.movie.databinding.ActivityShowListBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ShowListActivity : AppCompatActivity() {
    companion object Nav{
        lateinit var navView : BottomNavigationView
    }
    private lateinit var binding: ActivityShowListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        navView= binding.navView
//
        val navController = findNavController(R.id.nav_host_fragment_activity_show_list)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_movies, R.id.navigation_tv_shows, R.id.navigation_watch_later
            )
        )
//
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }


}