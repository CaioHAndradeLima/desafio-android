package com.araujoraul.desafioandroid.presentation.ui.pullrequests

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.data.model.PullRequest
import com.araujoraul.desafioandroid.util.Injection
import kotlinx.android.synthetic.main.activity_pull_request.*
import kotlinx.android.synthetic.main.toolbar.*

class PullRequestActivity : AppCompatActivity(), PullClickListener {

    private val adapter = PullRequestAdapter(this)
    private val viewModel by lazy {
        ViewModelProviders.of(this, Injection.providePullsViewModelFactory(this))
                .get(PullRequestsViewModel::class.java)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        setup()

    }

    fun setup() {
        val repository = intent.getStringExtra(REPOSITORY) ?: ""

        setSupportActionBar(toolbar)

        supportActionBar?.let {
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.title = repository
        }

        recyclerPull.layoutManager = LinearLayoutManager(this)
        recyclerPull.adapter = adapter


        viewModel.pullRequests.observe(this, Observer {
            Log.d("ActivityPull", "list: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapter.pulls.addAll(it)
           adapter.notifyDataSetChanged()
        })

        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(this, "\uD83D\uDE28 No connection! $it", Toast.LENGTH_LONG).show()
        })


//        TODO("Don't forget to called this name of repository in Title Toolbar")


    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyListPull.visibility = View.VISIBLE
            recyclerPull.visibility = View.GONE
        } else {
            emptyListPull.visibility = View.GONE
            recyclerPull.visibility = View.VISIBLE
        }
    }


    companion object{
        const val REPOSITORY = "REPOSITORY"

        fun createIntent(context: Context, repository: String) =
                Intent(context, PullRequestActivity::class.java).apply {
                    putExtra(REPOSITORY, repository)
                }
    }

    override fun onClick(pull: PullRequest) {
        Toast.makeText(applicationContext, "ALL RIGHT!", Toast.LENGTH_LONG).show()
//        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(pull.htmlUrl)))
        TODO("Not yet implemented")
    }


}

interface PullClickListener{
    fun onClick(pull: PullRequest)
}
