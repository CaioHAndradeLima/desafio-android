package com.araujoraul.desafioandroid.presentation.ui.reposlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class ReposListActivity : AppCompatActivity() {

    private val adapter = ReposListAdapter()
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ReposListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this

        binding.viewmodel = viewModel

        setupRecyclerAdapter()

    }

    fun setupRecyclerAdapter() {

        recyclerMain.layoutManager = LinearLayoutManager(this@ReposListActivity)
        recyclerMain.adapter = adapter
        viewModel.repos.observe(this, Observer {
            Log.d("Activity", "list: ${it?.size}")

            adapter.submitList(it)
        })

        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(this, "\uD83D\uDE28 Error! $it", Toast.LENGTH_LONG).show()
        })

    }

}
