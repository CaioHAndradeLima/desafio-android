package com.araujoraul.desafioandroid.presentation.ui.reposlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.data.model.Repository
import com.araujoraul.desafioandroid.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class ReposListActivity : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
setContentView(R.layout.activity_splash_screen)


        init()
    }

    fun init(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        with(recyclerMain){
            layoutManager = LinearLayoutManager(this@ReposListActivity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = ReposListAdapter(getRepositories())
        }

    }

    fun getRepositories(): List<Repository>{
        return listOf(Repository(1, "Fabricando App", "Descrição aqui", 20, 50, "aa" ) )
    }

}
