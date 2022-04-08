package com.example.memoryschest

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    private var mainMenu: Menu? = null
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

    // Memories Menu settings
//    fun showDeleteMenu(show: Boolean) {
//        mainMenu?.findItem(R.id.mDelete)?.isVisible = show
//    }
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        mainMenu = menu
//        menuInflater.inflate(R.menu.memories_menu, mainMenu)
//        showDeleteMenu(false)
//        return super.onCreateOptionsMenu(menu)
//    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId)
//        {
//            R.id.mDelete -> { deleteItems()}
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    private fun deleteItems() {
//
//    }


    private fun launchMainWidgets() {
        val modeViewButton : Button = findViewById(R.id.viewButton)
        val themeButton : Button = findViewById(R.id.themeButton)
        themeButton.setOnClickListener {
            themeChangeFromMain()
        }
        setFavoriteButtonDynamically(getUserTheme())


        // Here we create the adapter to the View Mode (Recycler view with images)
        val cardTitles : Array<String> = resources.getStringArray(R.array.cardTittles)
        val cardImages : Array<String> = resources.getStringArray(R.array.cardImages)
        val selectedValues : Array<String> = resources.getStringArray(R.array.cardSelected)
        val adapterImagesGrid = GridItemAdapter(cardTitles, cardImages, selectedValues)

        val adapterImagesSingle = SingleItemAdapter(cardTitles, cardImages)
        val currentModeView: String = getString(R.string.view_button_grid)

        if (modeViewButton.text.toString() == currentModeView) {
            val gridLayout = GridLayoutManager(this, 2)
            gridItems.layoutManager = gridLayout
            gridItems.adapter = adapterImagesGrid
        }
        else {
            val singleLayout = GridLayoutManager(this, 1)
            gridItems.layoutManager = singleLayout
            gridItems.adapter = adapterImagesSingle
        }

        // Here the View Mode can change dynamically
        modeViewButton.setOnClickListener() {
            if (modeViewButton.text.toString() == currentModeView) {
                modeViewButton.setText(R.string.view_button_single)
                val singleLayout = GridLayoutManager(this, 1)
                gridItems.layoutManager = singleLayout
                gridItems.adapter = adapterImagesSingle
            }
            else {
                modeViewButton.setText(R.string.view_button_grid)
                val gridLayout = GridLayoutManager(this, 2)
                gridItems.layoutManager = gridLayout
                gridItems.adapter = adapterImagesGrid
            }
        }
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
