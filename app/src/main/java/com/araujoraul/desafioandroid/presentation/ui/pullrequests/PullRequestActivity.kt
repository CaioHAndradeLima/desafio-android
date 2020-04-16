package com.araujoraul.desafioandroid.presentation.ui.pullrequests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.databinding.ActivityMainBinding
import com.araujoraul.desafioandroid.databinding.ActivityPullRequestBinding
import com.araujoraul.desafioandroid.util.Injection
import kotlinx.android.synthetic.main.activity_pull_request.*
import kotlinx.android.synthetic.main.toolbar.*

class PullRequestActivity : AppCompatActivity() {

    private val adapter = PullRequestAdapter(emptyList())
    private val viewModel by lazy {
        ViewModelProviders.of(this, Injection.providePullsViewModelFactory(this))
                .get(PullRequestsViewModel::class.java)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityPullRequestBinding = DataBindingUtil.setContentView(this, R.layout.activity_pull_request)

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        setup()

    }

    fun setup() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "NOME DO REPOSITORIO"
        TODO("Don't forget to called this name of repository in Title Toolbar")

        recyclerPull.layoutManager = LinearLayoutManager(this)
        recyclerPull.hasFixedSize()
        recyclerPull.adapter = adapter



//        viewModel.pu.observe(this, Observer {
//            Log.d("Activity", "list: ${it?.size}")
//            showEmptyList(it?.size == 0)
//            adapter.submitList(it)
//        })
//
//        viewModel.networkErrors.observe(this, Observer<String> {
//            Toast.makeText(this, "\uD83D\uDE28 No connection! $it", Toast.LENGTH_LONG).show()
//        })

    }


//    override fun onClick(item: PullRequest) {
//        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.htmlUrl)))
//    }


}
