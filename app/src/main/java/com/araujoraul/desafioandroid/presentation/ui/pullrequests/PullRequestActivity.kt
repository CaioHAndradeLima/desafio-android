package com.araujoraul.desafioandroid.presentation.ui.pullrequests

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.data.model.PullRequest
import com.araujoraul.desafioandroid.data.model.Repository
import com.araujoraul.desafioandroid.util.Injection
import kotlinx.android.synthetic.main.activity_pull_request.*
import kotlinx.android.synthetic.main.toolbar.*

class PullRequestActivity : AppCompatActivity(), PullClickListener {

    private val adapter = PullRequestAdapter(this)
    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerPull) }
    private val repository by lazy {
        intent.getParcelableExtra<Repository>(REPOSITORY)
                ?: throw IllegalStateException("please create Intent calling PullRequestActivity.createIntent")
    }

    private val viewModel by lazy {
        val repository = intent.getParcelableExtra<Repository>(REPOSITORY)
                ?: throw IllegalStateException("please create Intent calling PullRequestActivity.createIntent")

        ViewModelProviders.of(this, Injection.providePullsViewModelFactory(this,
                repository.owner.username,
                repository.name!!
        ))
                .get(PullRequestsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        setup()
    }

    private fun setup() {

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.title = repository.name
        }

        recyclerView.let {
            it.layoutManager = LinearLayoutManager(this)
            it.hasFixedSize()
            it.adapter = adapter
        }

        viewModel.pullRequests.observe(this, Observer {
            showEmptyList(it?.size == 0)
            adapter.pulls.addAll(it)
            adapter.notifyDataSetChanged()
        })

        viewModel.request()
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyListPull.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            emptyListPull.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }


    companion object {
        const val REPOSITORY = "REPOSITORY"

        fun createIntent(context: Context, repository: Repository) =
                Intent(context, PullRequestActivity::class.java).apply {
                    putExtra(REPOSITORY, repository)
                }
    }

    override fun onClick(pull: PullRequest) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(pull.htmlUrl)))
    }


}

interface PullClickListener {
    fun onClick(pull: PullRequest)
}
