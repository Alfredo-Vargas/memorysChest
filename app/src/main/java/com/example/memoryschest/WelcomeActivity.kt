package com.example.memoryschest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.Toast

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_welcome)
        val mainActivity: MainActivity = MainActivity()

        val themeIntent = Intent(this, MainActivity::class.java)

        val champagneBall: ImageView = findViewById(R.id.imageChampagne)
        val eerieBall: ImageView = findViewById(R.id.imageEerieBlack)
        val powderBall: ImageView = findViewById(R.id.imagePowderBlue)

        champagneBall.setOnClickListener {
            debugToastWelcome("WHAT", true)
            themeIntent.putExtra("selectedOption", "mainTheme")
            when (mainActivity.existUserTheme()){
                true -> {
                    debugToastWelcome("The UT exists!", true)
                    if (mainActivity.getUserTheme() == "mainTheme"){
                        startActivity(themeIntent)
                    }
                }
                else -> {
                    debugToastWelcome("The UT does NOT exist!", true)
                    mainActivity.setUserTheme("mainTheme")
                    mainActivity.applyUserThemeToMain("mainTheme")
                    startActivity(themeIntent)
                }
            }
        }
        eerieBall.setOnClickListener {
            themeIntent.putExtra("selectedOption", "darkTheme")
            when (mainActivity.existUserTheme()){
                true -> {
                    if (mainActivity.getUserTheme() == "darkTheme"){
                        startActivity(themeIntent)
                    }
                }
                else -> {
                    mainActivity.setUserTheme("darkTheme")
                    mainActivity.applyUserThemeToMain("darkTheme")
                    startActivity(themeIntent)
                }
            }
        }
        powderBall.setOnClickListener {
            themeIntent.putExtra("selectedOption", "lightTheme")
            when (mainActivity.existUserTheme()){
                true -> {
                    if (mainActivity.getUserTheme() == "lightTheme"){
                        startActivity(themeIntent)
                    }
                }
                else -> {
                    mainActivity.setUserTheme("lightTheme")
                    mainActivity.applyUserThemeToMain("lightTheme")
                    startActivity(themeIntent)
                }
            }
        }
    }

    fun debugToastWelcome(message: String, flag: Boolean) {
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
}