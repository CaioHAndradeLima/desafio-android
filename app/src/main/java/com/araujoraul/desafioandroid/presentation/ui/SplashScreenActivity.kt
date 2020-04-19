package com.araujoraul.desafioandroid.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.presentation.ui.reposlist.ReposListActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed(Runnable {
                startActivity(Intent(this, ReposListActivity::class.java))
                finish()
        }, 4000)

    }
}
