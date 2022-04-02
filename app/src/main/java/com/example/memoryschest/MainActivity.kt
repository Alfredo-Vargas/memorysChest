package com.example.memoryschest

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* To reset the shared preference for debugging
        val currentThemePreference: SharedPreferences =
            getSharedPreferences("theme", MODE_PRIVATE)
        currentThemePreference.edit().clear().commit()
         */

        // debugToastMain("we start main", true)
        when (existUserTheme()){
            true -> {
                applyUserThemeToMain(getUserTheme())
            }
            else -> {
                val showWelcome = Intent(this, WelcomeActivity::class.java)
                startActivity(showWelcome)
                val optionSelected: String? = intent.getStringExtra("selectedOption")
                setUserTheme(optionSelected)
                applyUserThemeToMain(optionSelected)
                launchMain()
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

    fun applyUserThemeToMain(themeChosen: String?){
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
            else -> {
                setTheme(R.style.mainTheme)
                launchMain()
            }
        }
    }

    fun debugToastMain(message: String, flag: Boolean) {
        Toast(this).apply {
            duration = Toast.LENGTH_SHORT
            setGravity(Gravity.CENTER, 0, 0)
            if (flag) {
                setText(message)
            }
            else {
                setText("Something went wrong")
            }
        }.show()
    }

    fun setUserTheme(themeChosen: String?) {
        // MODE_PRIVATE.- File creation mode: the default mode, where the created file can only
        // be accessed by the calling application (or all applications sharing the same user ID).
        val themePreferences: SharedPreferences = getSharedPreferences("theme", MODE_PRIVATE)
        val themeEditor = themePreferences.edit()
        // keep track of the theme chosen by THEME_KEY
        themeEditor.putString("THEME_KEY", themeChosen)
        // keep track of the theme changed in SAVED_THEME boolean
        themeEditor.putBoolean("EXISTS_THEME", !themeChosen.isNullOrBlank())
        // commit() applies instantly, apply() hold changes until next call
        themeEditor.apply()
        themeEditor.commit()
    }

    fun getUserTheme(): String? {
        val themePreferences: SharedPreferences = getSharedPreferences("theme", MODE_PRIVATE)
        return themePreferences.getString("THEME_KEY", null)
    }

    fun existUserTheme(): Boolean? {
        val themePreferences: SharedPreferences = getSharedPreferences("theme", MODE_PRIVATE)
        // we get the boolean value if user theme already exits
        return themePreferences.getBoolean("EXISTS_THEME", false)
    }
}
