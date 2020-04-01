package com.araujoraul.desafioandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarConfig()

    }

    fun toolbarConfig(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Github JavaPop"
    }

}
