package com.araujoraul.desafioandroid.presentation.ui.pullrequests

import androidx.lifecycle.*
import com.araujoraul.desafioandroid.data.api.GithubRepository

class PullRequestsViewModel(private val repository: GithubRepository) : ViewModel(){




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