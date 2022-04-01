package com.example.settings

import android.content.SharedPreferences

class UserPreferences () {
    fun loadProfile(up : SharedPreferences): Boolean {
        // we get the string value of our theme preference
        val themeChosen: String? = up.getString("THEME_KEY", null)
        //return themePreference.getBoolean("SAVED_THEME", false)
        return !themeChosen.isNullOrBlank()
    }

    fun saveProfile(tp : SharedPreferences, themeChosen: String?) {
        // we create an editor instance to be able to modify the preferences
        val themePreferenceEditor: SharedPreferences.Editor = tp.edit()
        themePreferenceEditor.apply {
            putString("THEME_KEY", themeChosen)     // save the dictionary key-pair
            // putBoolean("SAVED_THEME", if NullOrBlank means NOT SAVE_THEME available)
            putBoolean("SAVED_THEME", !themeChosen.isNullOrBlank())
        }.apply()
    }
}