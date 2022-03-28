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
}
