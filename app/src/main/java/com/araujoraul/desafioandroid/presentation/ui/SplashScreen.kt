package com.araujoraul.desafioandroid.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.presentation.ui.reposlist.ReposListActivity

class SplashScreen: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed(Runnable{
            run {
                startActivity(Intent(this, ReposListActivity::class.java))
                finish()
            }
        }, 3000)


    }
}