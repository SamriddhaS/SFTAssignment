package com.example.sftassignment.ui.splash_screen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.sftassignment.R
import com.example.sftassignment.ui.main_screen.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SFTAssignment_SplashTheme)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            /*
            * Showing the splash screen for 5sec
            * */
            delay(5000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finishAffinity()
        }
    }
}