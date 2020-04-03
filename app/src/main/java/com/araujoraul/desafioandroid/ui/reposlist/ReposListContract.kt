package com.araujoraul.desafioandroid.ui.reposlist

import android.view.View
import com.araujoraul.desafioandroid.data.model.Repositories
import com.araujoraul.desafioandroid.data.model.Repository

interface ReposListContract {

    interface ReposListView{
        fun displayRepositories(repositories: Repositories?)
        fun displayError()
        fun View.gone()
        fun View.show()
    }

    interface ReposListPresenter{
        fun getRepositories()
        fun destroyView()
    }

}