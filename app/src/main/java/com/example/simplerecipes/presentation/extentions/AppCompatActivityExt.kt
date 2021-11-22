package com.example.simplerecipes.presentation.extentions

import androidx.appcompat.app.AppCompatDelegate

fun enableDarkTheme(isEnabled: Boolean) {
    val uiMode =
        if (isEnabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
    AppCompatDelegate.setDefaultNightMode(uiMode)
}
