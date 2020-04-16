package com.araujoraul.desafioandroid.presentation.ui.pullrequests

import androidx.lifecycle.*
import com.araujoraul.desafioandroid.data.api.GithubRepository
import com.araujoraul.desafioandroid.data.model.Owner
import com.araujoraul.desafioandroid.data.model.PullRequest
import com.araujoraul.desafioandroid.data.model.PullRequestsResult

class PullRequestsViewModel(private val repository: GithubRepository) : ViewModel(){

    private val _pullRequests = MutableLiveData<PullRequestsResult>()

    val pullRequests: LiveData<List<PullRequest>> = Transformations.switchMap(_pullRequests){it -> it.data}
    val networkErrors: LiveData<String> = Transformations.switchMap(_pullRequests) { it -> it.networkErrors }


    class ViewModelFactory(private val pullRequest: GithubRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PullRequestsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PullRequestsViewModel(pullRequest) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")


            TODO("Class ViewModel not implemented yet!!!!")
        }
    }

}