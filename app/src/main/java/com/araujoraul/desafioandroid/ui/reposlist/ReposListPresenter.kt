package com.araujoraul.desafioandroid.ui.reposlist

import com.araujoraul.desafioandroid.data.model.Repositories
import com.araujoraul.desafioandroid.data.model.Repository
import com.araujoraul.desafioandroid.data.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReposListPresenter(private var view: ReposListContract.ReposListView?): ReposListContract.ReposListPresenter{

    companion object{
        const val LANGUAGE = "language:Java"
        const val SORT = "stars"
    }

    override fun getRepositories() {

        ApiService.instance!!
                .getJavaRepositories(LANGUAGE, SORT, 1)
                .enqueue(object : Callback<Repositories> {

                    override fun onResponse(call: Call<Repositories>, response: Response<Repositories>) {
                       if (response.isSuccessful){

                           val repositoriesList: Repositories = response.body()!!
                           view!!.displayRepositories(repositoriesList)
                       } else {
                           view!!.displayError()
                       }
                    }

                    override fun onFailure(call: Call<Repositories>, t: Throwable) {
                        view!!.displayError()
                    }

                })



    }

    override fun destroyView() {
        view = null
    }

}