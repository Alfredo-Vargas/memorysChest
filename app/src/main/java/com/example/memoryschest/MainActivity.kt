package com.example.memoryschest

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Run this one time to clear SharedPreferences or remove to save to system.
/*
        val currentThemePreference: SharedPreferences =
            getSharedPreferences("theme", MODE_PRIVATE)
        currentThemePreference.edit().clear().commit()
*/

        val optionSelected: String? = intent.getStringExtra("selectedOption")
        if (!optionSelected.isNullOrBlank()){
//            debugToastMain("Option selected is not null")
            setUserTheme(optionSelected)
        }
        val userThemePreference: String? = getUserTheme()
//        debugToastMain("$optionSelected and $userThemePreference")

        when (existUserTheme()) {
            true -> {
                setUserTheme(userThemePreference)
                applyUserThemeToMain(userThemePreference)
                launchMainWidgets()
            }
            else -> {
                val showWelcome = Intent(this, WelcomeActivity::class.java)
                startActivity(showWelcome)
            }
        }
    }

    private fun launchMainWidgets() {
        val themeButton : Button = findViewById(R.id.themeButton)
        themeButton.setOnClickListener() {
            themeChangeFromMain()
        }
        setFavoriteButtonDynamically(getUserTheme())

        // Here we create the adapter
        val cardTitles : Array<String> = resources.getStringArray(R.array.cardTittles)
        val cardImages : Array<String> = resources.getStringArray(R.array.cardImages)
        val adapterImages = GridItemAdapter(cardTitles, cardImages)
        // spanCount the number of items side by side (in a single row)
        val gridLayout = GridLayoutManager(this, 4)
        gridItems.layoutManager = gridLayout
        gridItems.adapter = adapterImages
    }

    private fun applyUserThemeToMain(themeChosen: String?){
        when (themeChosen) {
            "mainTheme" -> {
                theme.applyStyle(R.style.mainTheme, true)
                setContentView(R.layout.activity_main)
            }
            "darkTheme" -> {
                theme.applyStyle(R.style.darkTheme, true)
                setContentView(R.layout.activity_main)
            }
            "lightTheme" -> {
                theme.applyStyle(R.style.lightTheme, true)
                setContentView(R.layout.activity_main)
            }
            else -> {
                theme.applyStyle(R.style.mainTheme, true)
                setContentView(R.layout.activity_main)
            }
        }
    }

    private fun setUserTheme(themeChosen: String?) {
        // MODE_PRIVATE.- File creation mode: the default mode, where the created file can only
        // be accessed by the calling application (or all applications sharing the same user ID).
        val themePreferences: SharedPreferences = getSharedPreferences("theme", MODE_PRIVATE)
        val themeEditor = themePreferences.edit()
        // keep track of the theme chosen by THEME_KEY
        themeEditor.putString("THEME_KEY", themeChosen)
        // keep track of the theme changed in SAVED_THEME boolean
        themeEditor.putBoolean("EXISTS_THEME", !themeChosen.isNullOrBlank())
        // commit() applies instantly, apply() applies changes in the background
        themeEditor.apply()
    }

    private fun getUserTheme(): String? {
        val themePreferences: SharedPreferences = getSharedPreferences("theme", MODE_PRIVATE)
        return themePreferences.getString("THEME_KEY", null)
    }

    private fun existUserTheme(): Boolean? {
        val themePreferences: SharedPreferences = getSharedPreferences("theme", MODE_PRIVATE)
        // we get the boolean value if user theme already exits
        return themePreferences.getBoolean("EXISTS_THEME", false)
    }

    fun debugToastMain(message: String) {
        Toast(this).apply {
            duration = Toast.LENGTH_SHORT
            setGravity(Gravity.CENTER, 0, 0)
            setText(message)
        }.show()
    }

    private fun setFavoriteButtonDynamically(theme: String?) {
        val favoriteButton : ImageButton = findViewById(R.id.favoriteButton)
        when (theme) {
            "mainTheme" -> {
                favoriteButton.setImageResource(R.drawable.ic_heart_main)
            }
            "darkTheme" -> {
                favoriteButton.setImageResource(R.drawable.ic_heart_dark)
            }
            "lightTheme" -> {
                favoriteButton.setImageResource(R.drawable.ic_heart_light)
            }
            else -> {
                favoriteButton.setImageResource(R.drawable.ic_heart_main)
            }
        }
    }

    private fun themeChangeFromMain() {
        val showWelcome = Intent(this, WelcomeActivity::class.java)
        startActivity(showWelcome)
        val optionSelected: String? = intent.getStringExtra("selectedOption")
        setUserTheme(optionSelected)
        applyUserThemeToMain(optionSelected)
        launchMainWidgets()
    }
}
