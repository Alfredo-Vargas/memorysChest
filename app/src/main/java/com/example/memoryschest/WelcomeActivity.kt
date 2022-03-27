package com.example.memoryschest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val themeIntent = Intent(this, MainActivity::class.java)
        val champagneBall: ImageView = findViewById(R.id.imageChampagne)
        val eerieBall: ImageView = findViewById(R.id.imageEerieBlack)
        val powderBall: ImageView = findViewById(R.id.imagePowderBlue)
        champagneBall.setOnClickListener {
            themeIntent.putExtra("selectedOption", "mainTheme")
            startActivity(themeIntent)
        }
        eerieBall.setOnClickListener {
            themeIntent.putExtra("selectedOption", "darkTheme")
            startActivity(themeIntent)
        }
        powderBall.setOnClickListener {
            themeIntent.putExtra("selectedOption", "lightTheme")
            startActivity(themeIntent)
        }
    }
}