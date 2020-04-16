package com.araujoraul.desafioandroid.data.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.araujoraul.desafioandroid.data.model.PullRequestsResult
import com.araujoraul.desafioandroid.data.model.RepositoriesResult
import com.araujoraul.desafioandroid.db.GithubLocalCache

class GithubRepository (private val service: RepositoriesService, private val cache: GithubLocalCache) {

    private var lastRequestedPage = 1
    private val networkErrors = MutableLiveData<String>()
    private var isRequestInProgress = false

    /**
     * Search repositories whose names match the query.
     */
    fun searchRepos(query: String): RepositoriesResult {
        Log.d("GithubRepository", "New query: $query")
        lastRequestedPage = 1
        requestReposAndSaveData(query)

        // Get data from the local cache
        val data = cache.reposByName(query)

        return RepositoriesResult(data, networkErrors)
    }

    fun requestMore(query: String) {
        requestReposAndSaveData(query)
    }

    private fun requestReposAndSaveData(query: String) {
        if (isRequestInProgress) return

        isRequestInProgress = true
        searchJavaPopRepositories(service, query, lastRequestedPage, NETWORK_PAGE_SIZE, { repos ->
            cache.insert(repos) {
                lastRequestedPage++
                isRequestInProgress = false
            }
        }, { error ->
            networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }


     fun requestPullsAndSaveData(owner: String, repo: String){
            TODO("??????????")
        searchPullRequests(service, owner, repo, { pulls ->
            cache.insertPull(pulls){}
        }, { error ->
            networkErrors.postValue(error)
        })

    }



    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}