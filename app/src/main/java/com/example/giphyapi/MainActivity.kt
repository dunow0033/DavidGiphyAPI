package com.example.giphyapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.giphyapi.Repository.GiphyRepository
import com.example.giphyapi.data.remote.GiphyManager
import com.example.giphyapi.databinding.ActivityMainBinding
import com.example.giphyapi.ui.GiphyViewModel
import com.example.giphyapi.ui.GiphyViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GiphyViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, GiphyViewModelFactory(GiphyRepository(GiphyManager()))).get(
                GiphyViewModel::class.java
            )


        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment)

        setupActionBarWithNavController(navController)
        navView.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}