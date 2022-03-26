package com.example.memoryschest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.SharedPreferences;

class MainActivity : AppCompatActivity() {
    private val prevStarted: String = "yes";

    override fun onResume() {
        super.onResume();
        val preferences: SharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        // We check if prevStarted matches false, then to be executed we negate it with ! at the beginning
        if (!preferences.getBoolean(prevStarted, false))  {
            // we create an instance that allows to write to preferences
            val editor : SharedPreferences.Editor = preferences.edit();
            editor.putBoolean(prevStarted, true);
            editor.apply();
        }
        else {
            mainPage();
        }
    }

    // normal starting main activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    // mainPage needs to public to pass the user's theme and change the theme when necessary
    fun mainPage() {
        // here we need to use an intent to pass the current theme and start the activity_main
        val intentToMain: Intent = Intent(this, MainActivity::class.java);

    }
}