package com.ch.movie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.ch.movie.R
import com.ch.movie.databinding.ActivityShowListBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowListActivity : AppCompatActivity() {
    lateinit var navView : BottomNavigationView
    private lateinit var binding: ActivityShowListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        navView= binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_show_list)

        AppBarConfiguration(
            setOf(
                R.id.navigation_movies, R.id.navigation_tv_shows, R.id.navigation_watch_later
            )
        )
    navView.setupWithNavController(navController)

    }
}

