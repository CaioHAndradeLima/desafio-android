package com.araujoraul.desafioandroid.ui.reposlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.data.model.Repositories
import com.araujoraul.desafioandroid.data.model.Repository
import com.araujoraul.desafioandroid.ui.repodetail.RepoDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class ReposListActivity : AppCompatActivity(), ReposListAdapter.ItemRepoClickListener, ReposListContract.ReposListView {

    private var presenter: ReposListPresenter? = null

    private val adapter: ReposListAdapter by lazy {
        ReposListAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      init()
        presenter = ReposListPresenter(this)
        presenter!!.getRepositories()

    }

    private fun init(){
        toolbarConfig()
        recyclerViewConfig()

    }

    private fun recyclerViewConfig() {
        recyclerView_main.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        recyclerView_main.layoutManager = layoutManager
    }

    fun toolbarConfig(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Github JavaPop"
    }

    override fun onClick(item: Repository) {
      startActivity(
              RepoDetailActivity.createIntent(
                      context = this,
                      repository = item.name,
                      creator = item.owner.login
              )
      )
    }

    override fun displayRepositories(repositories: Repositories?) {
        adapter.setRepositories(repositories)
    }


    override fun displayError() {
        Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_LONG)
    }

    override fun View.gone() {
        TODO("Not yet implemented")
    }

    override fun View.show() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.destroyView()
    }

}
