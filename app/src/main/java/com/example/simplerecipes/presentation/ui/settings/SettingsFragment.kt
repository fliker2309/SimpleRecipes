package com.example.simplerecipes.presentation.ui.settings

import android.content.res.Configuration
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.example.simplerecipes.R
import com.example.simplerecipes.presentation.extentions.enableDarkTheme
import com.example.simplerecipes.utils.Constants.ENABLE_DARK_THEME
import dagger.hilt.android.AndroidEntryPoint

private const val VERSION = "version"

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        findPreference<SwitchPreference>(ENABLE_DARK_THEME)?.apply {
            onPreferenceChangeListener = this@SettingsFragment
            isChecked = getUiMode() == Configuration.UI_MODE_NIGHT_YES
        }
        val context = requireContext()
        val versionName = context.packageManager.getPackageInfo(context.packageName, 0).versionName
        findPreference<Preference>(VERSION)?.summary = versionName
    }

    private fun getUiMode() =
        requireContext().resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        when (preference?.key) {
            ENABLE_DARK_THEME -> enableDarkTheme(newValue as Boolean)
        }
        return true
    }
}
