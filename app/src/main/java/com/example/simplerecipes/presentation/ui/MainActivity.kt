package com.example.simplerecipes.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.simplerecipes.R
import com.example.simplerecipes.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.bottomNav

        val navController = findNavController(R.id.navHostFragmentActivityMain)

        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.categoriesFragment -> navView.visibility = View.VISIBLE
                R.id.searchFragment -> navView.visibility = View.VISIBLE
                R.id.favoritesFragment -> navView.visibility = View.VISIBLE
                R.id.settingsFragment -> navView.visibility = View.VISIBLE
                else -> navView.visibility = View.GONE
            }
        }
    }
}
