package com.araujoraul.desafioandroid.presentation.ui.reposlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.data.model.Repository
import com.araujoraul.desafioandroid.databinding.ActivityMainBinding
import com.araujoraul.desafioandroid.presentation.ui.pullrequests.PullRequestActivity
import com.araujoraul.desafioandroid.util.Injection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_repos_list.*
import kotlinx.android.synthetic.main.toolbar.*


class ReposListActivity : AppCompatActivity(), RepoClickListener {

    private val adapter = ReposListAdapter(this)
    private val viewModel by lazy {
        ViewModelProviders.of(this, Injection.provideReposViewModelFactory(this))
                .get(ReposListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel


        setupScrollListener()
        setup()
        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        viewModel.searchRepo(query)
        initSearch(query)

    }

    fun setup() {

        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        recyclerMain.adapter = adapter
        viewModel.repos.observe(this, Observer {
            Log.d("Activity", "list: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })

        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(this, "\uD83D\uDE28 No connection! $it", Toast.LENGTH_LONG).show()
        })



    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_SEARCH_QUERY, viewModel.lastQueryValue())
    }

    private fun initSearch(query: String) {
        search_repo.setText(query)

        search_repo.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateRepoListFromInput()
                true
            } else {
                false
            }
        }
        search_repo.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateRepoListFromInput()
                true
            } else {
                false
            }
        }
    }

    private fun updateRepoListFromInput() {
        search_repo.text.trim().let {
            if (it.isNotEmpty()) {
                recyclerMain.scrollToPosition(0)
                viewModel.searchRepo(it.toString())
                adapter.submitList(null)
            }
        }
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyList.visibility = View.VISIBLE
            recyclerMain.visibility = View.GONE
        } else {
            emptyList.visibility = View.GONE
            recyclerMain.visibility = View.VISIBLE
        }
    }

    private fun setupScrollListener() {
        val layoutManager = recyclerMain.layoutManager as LinearLayoutManager
        recyclerMain.addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                viewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
            }
        })
    }

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = "MVVM"
    }

    override fun onClick(repository: Repository) {
        startActivity(
                PullRequestActivity.createIntent(
                        context = this,
                        repository = repository.name.toString()
                )
        )
    }

}

interface RepoClickListener {
    fun onClick(repository: Repository)
}
