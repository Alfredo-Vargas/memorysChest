package com.example.memoryschest

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.memoryschest.data.Photo
import com.example.memoryschest.data.PhotoViewModel
import com.example.memoryschest.models.CardItemValues
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mainMenu: Menu? = null
    private lateinit var mPhotoViewModel: PhotoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPhotoViewModel = ViewModelProvider(this)[PhotoViewModel::class.java]

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

    fun showDeleteMenu(show: Boolean) {
        mainMenu?.findItem(R.id.mDelete)?.isVisible = show
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.memories_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mDelete -> { delete() }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun delete() {
        var itemsToDelete: String = getString(R.string.pre_message_deletion) + "\n"
        var isDeletionAllowd: Boolean = false
        for (si in listCardItemValues) {
            if (si.selected) {
                isDeletionAllowd = true
                itemsToDelete += " " + si.number + ", "
            }
        }
        if (isDeletionAllowd) {
            debugToastMain(itemsToDelete)
        }
        else {
            debugToastMain(getString(R.string.post_message_deletion))
        }
    }

    val listCardItemValues = mutableListOf<CardItemValues>(
        CardItemValues(1, false),
        CardItemValues(2, false),
        CardItemValues(3, false),
        CardItemValues(4, false),
        CardItemValues(5, false),
        CardItemValues(6, false),
        CardItemValues(7, false),
        CardItemValues(8, false),
        CardItemValues(9, false),
        CardItemValues(10, false),
        CardItemValues(11, false),
        CardItemValues(12, false),
        CardItemValues(13, false),
        CardItemValues(14, false),
        CardItemValues(15, false),
        CardItemValues(16, false),
        CardItemValues(17, false),
        CardItemValues(18, false),
        CardItemValues(19, false),
        CardItemValues(20, false),
        CardItemValues(21, false),
        CardItemValues(22, false),
        CardItemValues(23, false),
        CardItemValues(24, false),
        CardItemValues(25, false),
        CardItemValues(26, false),
        CardItemValues(27, false),
        CardItemValues(28, false),
        CardItemValues(29, false),
        CardItemValues(30, false),
        CardItemValues(31, false),
        CardItemValues(32, false),
        CardItemValues(33, false),
        CardItemValues(34, false),
        CardItemValues(35, false),
        CardItemValues(36, false),
        CardItemValues(37, false),
        CardItemValues(38, false),
        CardItemValues(39, false),
        CardItemValues(40, false),
        CardItemValues(41, false),
        CardItemValues(42, false),
        CardItemValues(43, false),
        CardItemValues(44, false),
        CardItemValues(45, false),
        CardItemValues(46, false),
        CardItemValues(47, false),
        CardItemValues(48, false),
        CardItemValues(49, false),
        CardItemValues(50, false),
        CardItemValues(51, false),
    )

    private fun launchMainWidgets() {
        val modeViewButton : Button = findViewById(R.id.viewButton)
        val favoriteViewButton: ImageButton = findViewById(R.id.favoriteButton)
        val themeButton : Button = findViewById(R.id.themeButton)
        themeButton.setOnClickListener {
            themeChangeFromMain()
        }
        setFavoriteButtonDynamically(getUserTheme())


        // Here we create the adapter to the View Mode (Recycler view with images)
        // We fetch the images from internet (connection required!)

        val cardTitles : Array<String> = resources.getStringArray(R.array.cardTittles)
        val cardImages : Array<String> = resources.getStringArray(R.array.cardImages)
        val adapterImagesGrid = GridItemAdapter(listCardItemValues, cardTitles, cardImages){show -> showDeleteMenu(show)}

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

        // Here we implement logic of add pictures to favorites
        favoriteViewButton.setOnClickListener() {
            var itemsToAddToFavorites: String = getString(R.string.pre_message_favorites) + "\n"
            var isAdditionAllowed: Boolean = false
            for (si in listCardItemValues) {
                if (si.selected) {
                    isAdditionAllowed = true
                    itemsToAddToFavorites += " " + si.number + ", "
                }
            }
            if (isAdditionAllowed) {
                debugToastMain(itemsToAddToFavorites)
            }
            else {
                debugToastMain(getString(R.string.post_message_favorites))
            }
        }
        // We populate the database here
        var counter: Int = 0
        for (cardItem in listCardItemValues) {
            if (counter % 2 == 0) {
                val photo = Photo(0, "Belgium", "Samsung")
                mPhotoViewModel.addPhoto(photo)
            }
            else {
                val photo = Photo(0, "Italy", "Iphone")
                mPhotoViewModel.addPhoto(photo)
            }
            counter++
        }
        debugToastMain("Photo's info were added to database succesfully")
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
            duration = Toast.LENGTH_LONG
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
