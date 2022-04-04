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
        val currentThemePreference: SharedPreferences =
            getSharedPreferences("theme", MODE_PRIVATE)
        currentThemePreference.edit().clear().commit()

//        val answer: String = existUserTheme().toString()
//        debugToastMain(answer)

        when (existUserTheme()){
            true -> {
                applyUserThemeToMain(getUserTheme())
                launchMainWidgets()
            }
            else -> {
                val showWelcome = Intent(this, WelcomeActivity::class.java)
                startActivity(showWelcome)
                val optionSelected: String? = intent.getStringExtra("selectedOption")
                setUserTheme(optionSelected)
                applyUserThemeToMain(optionSelected)
                if (!optionSelected.isNullOrBlank()){
                    debugToastMain("Selected: $optionSelected")
                }
                launchMainWidgets()
            }
        }
    }

    private fun launchMainWidgets() {
        var themeButton : Button = findViewById(R.id.themeButton)
        themeButton.setOnClickListener() {
            themeChangeFromMain()
        }

        // Here we create the adapter
        val cardTitles : Array<String> = resources.getStringArray(R.array.cardTittles)
        val cardImages : Array<String> = resources.getStringArray(R.array.cardImages)
        val adapterImages = GridItemAdapter(cardTitles, cardImages)
        // spanCount the number of items side by side (in a single row)
        val gridLayout = GridLayoutManager(this, 2)
        gridItems.layoutManager = gridLayout
        gridItems.adapter = adapterImages
    }

    fun applyUserThemeToMain(themeChosen: String?){
        when (themeChosen) {
            "mainTheme" -> {
                theme.applyStyle(R.style.mainTheme, true)
                this.setContentView(R.layout.activity_main)
                //setTheme(R.style.mainTheme)
            }
            "darkTheme" -> {
                theme.applyStyle(R.style.darkTheme, true)
                this.setContentView(R.layout.activity_main)
                //setTheme(R.style.darkTheme)
            }
            "lightTheme" -> {
                theme.applyStyle(R.style.lightTheme, true)
                this.setContentView(R.layout.activity_main)
                //setTheme(R.style.lightTheme)
            }
            else -> {
                theme.applyStyle(R.style.mainTheme, true)
                this.setContentView(R.layout.activity_main)
                //setTheme(R.style.mainTheme)
            }
        }
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
        // commit() applies instantly, apply() hold changes until next
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

    private fun debugToastMain(message: String) {
        Toast(this).apply {
            duration = Toast.LENGTH_SHORT
            setGravity(Gravity.CENTER, 0, 0)
            setText(message)
        }.show()
    }

    fun themeChangeFromMain() {
        val showWelcome = Intent(this, WelcomeActivity::class.java)
        startActivity(showWelcome)
        val optionSelected: String? = intent.getStringExtra("selectedOption")
        setUserTheme(optionSelected)
        applyUserThemeToMain(optionSelected)
        launchMainWidgets()
    }
}
