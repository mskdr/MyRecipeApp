package com.muhammetkdr.myrecipeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.muhammetkdr.myrecipeapp.R
import com.muhammetkdr.myrecipeapp.common.extensions.gone
import com.muhammetkdr.myrecipeapp.common.extensions.visible
import com.muhammetkdr.myrecipeapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val view = binding.root
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(view)

        // Bottom Navigation Menu
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        // adjustment for meals fragment
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.homeFragment -> navHostFragment.findNavController().navigate(R.id.homeFragment)
                R.id.searchFragment -> navHostFragment.findNavController().navigate(R.id.searchFragment)
                R.id.favoritesFragment-> navHostFragment.findNavController().navigate(R.id.favoritesFragment)
            }
            true
        }

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment, R.id.searchFragment, R.id.favoritesFragment,R.id.mealsFragment -> {
                    binding.bottomNavigationView.visible()
                }
                else -> {
                    binding.bottomNavigationView.gone()
                }
            }
        }
    }
}