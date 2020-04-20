package com.araujoraul.desafioandroid.presentation.ui.reposlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.data.model.Repository
import com.araujoraul.desafioandroid.presentation.ui.pullrequests.PullRequestActivity
import com.araujoraul.desafioandroid.presentation.ui.reposlist.adapter.ReposListAdapter
import com.araujoraul.desafioandroid.util.Injection
import kotlinx.android.synthetic.main.activity_main.*


class ReposListActivity : AppCompatActivity(), RepoClickListener {

    private val adapter = ReposListAdapter(this)
    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerMain) }
    private val viewModel by lazy {
        ViewModelProviders.of(this, Injection.provideReposViewModelFactory(this))
                .get(ReposListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
        viewModel.searchRepo()
    }

    fun setup() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        recyclerView.let {
            it.layoutManager = LinearLayoutManager(this)
            it.hasFixedSize()
            it.adapter = adapter
        }

        viewModel.repos.observe(this, Observer {
            Log.d("Activity", "list: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })

        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(this, "\uD83D\uDE28 No connection! $it", Toast.LENGTH_LONG).show()
        })
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyList.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            emptyList.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    override fun onClick(repository: Repository) {
        startActivity(
                PullRequestActivity.createIntent(
                        context = this,
                        repository = repository
                )
        )
    }

}

interface RepoClickListener {
    fun onClick(repository: Repository)
}
