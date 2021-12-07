package com.example.simplerecipes.presentation.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.example.simplerecipes.R
import com.example.simplerecipes.databinding.ActivityMainBinding
import com.example.simplerecipes.presentation.extentions.enableDarkTheme
import com.example.simplerecipes.utils.Constants.ENABLE_DARK_THEME
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
                R.id.searchFragment -> navView.visibility = View.VISIBLE
                R.id.favoritesFragment -> navView.visibility = View.VISIBLE
                R.id.settingsFragment -> navView.visibility = View.VISIBLE
                else -> navView.visibility = View.GONE
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(newBase)
        setUpUiMode(sharedPreferences)
    }

    private fun setUpUiMode(sharedPreferences: SharedPreferences) {
        if (sharedPreferences.contains(ENABLE_DARK_THEME)) {
            enableDarkTheme(sharedPreferences.getBoolean(ENABLE_DARK_THEME, false))
        }
    }
}
