package com.araujoraul.desafioandroid.presentation.ui.pullrequests

import androidx.lifecycle.*
import com.araujoraul.desafioandroid.data.api.GithubRepository
import com.araujoraul.desafioandroid.data.model.PullRequest
import java.util.concurrent.Executors

class PullRequestsViewModel(
        private val repository: GithubRepository,
        private val owner: String,
        private val repo: String
) : ViewModel() {

    private val _pullRequests = MutableLiveData<Unit>()

    private val pullResult: LiveData<List<PullRequest>> = Transformations.map(_pullRequests) {
        PullRequestRepository(repository).request(owner, repo)
    }

    val pullRequests: LiveData<List<PullRequest>> = pullResult

    fun request() {
        _pullRequests.postValue(Unit)
    }

    class ViewModelFactory(private val pullRequest: GithubRepository,private val owner: String,private val repo: String) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PullRequestsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PullRequestsViewModel(pullRequest, owner, repo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}