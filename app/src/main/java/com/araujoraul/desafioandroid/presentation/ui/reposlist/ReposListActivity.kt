package com.araujoraul.desafioandroid.presentation.ui.reposlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.databinding.ActivityMainBinding


class ReposListActivity : AppCompatActivity(){

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ReposListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this

        binding.viewmodel = viewModel


    }

}
