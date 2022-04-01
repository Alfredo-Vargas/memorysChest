package com.example.memoryschest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.SharedPreferences
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // normal starting main activity
    override fun onCreate(savedInstanceState: Bundle?) {
        val themeC: String = ""
        super.onCreate(savedInstanceState)
        // load the preferences related to the app name
        //val preferences: SharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        val showWelcome = Intent(this, WelcomeActivity::class.java)
        val themeChosen: String? = intent.getStringExtra("selectedOption")
        when (themeChosen) {
            "mainTheme" -> {
                setTheme(R.style.mainTheme)
                launchMain()
            }
            "darkTheme" -> {
                setTheme(R.style.darkTheme)
                launchMain()
            }
            "lightTheme" -> {
                setTheme(R.style.lightTheme)
                launchMain()
            }
            null -> {
                startActivity(showWelcome)
            }
        }
    }

    private fun launchMain() {
        setContentView(R.layout.activity_main)
        val spinner: Spinner = findViewById(R.id.themeButton)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.theme,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }

    private fun saveProfile() {
        // we load the string from resources which initially has an empty string ""
        val themeChosen: String = R.string.chosen_theme_value.toString()
        // now create an instance of SharedPreferences whose value is given by the previous one
        val themePreference: SharedPreferences = getSharedPreferences("chosenTheme", Context.MODE_PRIVATE)
        // we create an editor instance to be able to modify the preferences
        val themePreferenceEditor: SharedPreferences.Editor = themePreference.edit()
        themePreferenceEditor.apply(){
            putString("THEME_KEY", themeChosen)     // save the dictionary key-pair
            putBoolean("SAVED_THEME", !themeChosen.isNullOrBlank())
        }.apply()
        // here to insert the Data saved
    }

    private fun loadProfile() {
        // we load the chosen theme preference of the user
        val themePreference: SharedPreferences = getSharedPreferences("chosenTheme", Context.MODE_PRIVATE)
        val themeChosen: String? = themePreference.getString("THEME_KEY", null)     // default value null
        val themeSaved: Boolean = themePreference.getBoolean("SAVED_THEME", false)  // default value false
    }
}









































