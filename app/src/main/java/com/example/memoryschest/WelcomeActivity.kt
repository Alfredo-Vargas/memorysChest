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
        val themeIntent = Intent(this, MainActivity::class.java)

        val champagneBall: ImageView = findViewById(R.id.imageChampagne)
        val eerieBall: ImageView = findViewById(R.id.imageEerieBlack)
        val powderBall: ImageView = findViewById(R.id.imagePowderBlue)


//        val activityFromMain: MainActivity = MainActivity()
        champagneBall.setOnClickListener {
            themeIntent.putExtra("selectedOption", "mainTheme")
//            activityFromMain.setUserTheme("mainTheme")
//            activityFromMain.applyUserThemeToMain("mainTheme")
            startActivity(themeIntent)
        }
        eerieBall.setOnClickListener {
            themeIntent.putExtra("selectedOption", "darkTheme")
//            activityFromMain.setUserTheme("darkTheme")
//            activityFromMain.applyUserThemeToMain("darkTheme")
            startActivity(themeIntent)
        }
        powderBall.setOnClickListener {
            themeIntent.putExtra("selectedOption", "lightTheme")
//            activityFromMain.setUserTheme("lightTheme")
//            activityFromMain.applyUserThemeToMain("lightTheme")
            startActivity(themeIntent)
        }
    }

    fun debugToastWelcome(message: String) {
        Toast(this).apply {
            duration = Toast.LENGTH_SHORT
            setGravity(Gravity.CENTER, 0, 0)
            setText(message)
        }.show()
    }
}