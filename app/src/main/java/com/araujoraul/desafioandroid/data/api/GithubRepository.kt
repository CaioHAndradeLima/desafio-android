package com.araujoraul.desafioandroid.data.api

import androidx.lifecycle.MutableLiveData
import com.araujoraul.desafioandroid.data.model.PullRequest
import com.araujoraul.desafioandroid.data.model.RepositoriesResult
import com.araujoraul.desafioandroid.db.GithubLocalCache

class GithubRepository (private val service: RepositoriesService, private val cache: GithubLocalCache) {

    private var lastRequestedPage = 1
    private val networkErrors = MutableLiveData<String>()
    private var isRequestInProgress = false

    /**
     * Search repositories whose names match the query.
     */
    fun searchRepos(): RepositoriesResult {
        lastRequestedPage = 1
        requestReposAndSaveData()

        // Get data from the local cache
        val data = cache.reposByName()

        return RepositoriesResult(data, networkErrors)
    }

    fun requestMore() {
        requestReposAndSaveData()
    }

    private fun requestReposAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        searchJavaPopRepositories(service, lastRequestedPage, NETWORK_PAGE_SIZE, { repos ->
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
        searchPullRequests(service, owner, repo, { pulls ->
            cache.insertPull(pulls){}
        }, { error ->
            networkErrors.postValue(error)
        })
    }


    fun searchPulls() = cache.searchPull()

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}