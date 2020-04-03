package com.araujoraul.desafioandroid.ui.repodetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.data.model.PullRequests
import kotlinx.android.synthetic.main.activity_repo_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class RepoDetailActivity : AppCompatActivity(), RepoDetailAdapter.ItemRepoDetailClickListener {

    private val adapter by lazy {
        RepoDetailAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)

        init()

    }

    fun init(){
    val creator = intent?.getStringExtra(CREATOR)?: ""
        val repository = intent?.getStringExtra(REPOSITORY)?: ""

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = repository

        recyclerView_detail.layoutManager = LinearLayoutManager(this)
        recyclerView_detail.adapter = adapter

    }


    companion object {

        const val CREATOR = "CREATOR"
        const val REPOSITORY = "REPOSITORY"

        fun createIntent(context: Context, creator: String, repository: String) =
                Intent(context, RepoDetailActivity::class.java).apply {
                    putExtra(CREATOR, creator)
                    putExtra(REPOSITORY, repository)
                }
    }

    override fun onClick(item: PullRequests) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.htmlUrl)))
    }


}
