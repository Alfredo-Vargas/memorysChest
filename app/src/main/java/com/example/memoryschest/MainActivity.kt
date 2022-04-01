package com.example.memoryschest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.SharedPreferences
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    // normal starting main activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (loadProfile()) {
            val themePreference: SharedPreferences =
                getSharedPreferences("chosenTheme", Context.MODE_PRIVATE)
            // to reset the shared preference for debugging
            // themePreference.edit().clear().commit()
            val themeChosen: String? = themePreference.getString("THEME_KEY", null)
            setProfileStartMain(themeChosen)
        }
        else {
            val showWelcome = Intent(this, WelcomeActivity::class.java)
            startActivity(showWelcome)
            // we get the value that comes from the welcome activity
            val optionSelected: String? = intent.getStringExtra("selectedOption")
            // we save the value to our theme preferences
            saveProfile(optionSelected)
            // we load our theme preference
            val themePreference: SharedPreferences =
                getSharedPreferences("chosenTheme", Context.MODE_PRIVATE)
            // we get the string value of our theme preference
            val themeChosen: String? = themePreference.getString("THEME_KEY", null)
            // we start the main activity based on the value chosen
            setProfileStartMain(themeChosen)
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

    private fun saveProfile(themeChosen: String?) {
        // now create an instance of SharedPreferences whose value is given by the previous one
        val themePreference: SharedPreferences =
            getSharedPreferences("chosenTheme", Context.MODE_PRIVATE)
        // we create an editor instance to be able to modify the preferences
        val themePreferenceEditor: SharedPreferences.Editor = themePreference.edit()
        themePreferenceEditor.apply {
            putString("THEME_KEY", themeChosen)     // save the dictionary key-pair
            // putBoolean("SAVED_THEME", if NullOrBlank means NOT SAVE_THEME available)
            putBoolean("SAVED_THEME", !themeChosen.isNullOrBlank())
        }.apply()
    }

    private fun loadProfile(): Boolean {
        // we load the chosen theme preference of the user
        val themePreference: SharedPreferences =
            getSharedPreferences("chosenTheme", Context.MODE_PRIVATE)
        // we get the string value of our theme preference
        val themeChosen: String? = themePreference.getString("THEME_KEY", null)
        //return themePreference.getBoolean("SAVED_THEME", false)
        return !themeChosen.isNullOrBlank()
    }

    private fun setProfileStartMain(themeChosen: String?){
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
        }
    }
}
