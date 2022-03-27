package com.example.memoryschest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.SharedPreferences
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
                setContentView(R.layout.activity_main)
            }
            "darkTheme" -> {
                setTheme(R.style.darkTheme)
                setContentView(R.layout.activity_main)
            }
            "lightTheme" -> {
                setTheme(R.style.lightTheme)
                setContentView(R.layout.activity_main)
            }
            null -> {
                startActivity(showWelcome)
            }
        }
    }
}
