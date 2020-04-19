package com.araujoraul.desafioandroid.presentation.ui.reposlist

import androidx.lifecycle.*
import com.araujoraul.desafioandroid.data.api.GithubRepository
import com.araujoraul.desafioandroid.data.model.RepositoriesResult
import com.araujoraul.desafioandroid.data.model.Repository

class ReposListViewModel(private val repository: GithubRepository) : ViewModel() {

    private val queryLiveData = MutableLiveData<Unit>()
    private val repoResult: LiveData<RepositoriesResult> = Transformations.map(queryLiveData) {
        repository.searchRepos()
    }

    val repos: LiveData<List<Repository>> = Transformations.switchMap(repoResult) { it -> it.data }
    val networkErrors: LiveData<String> = Transformations.switchMap(repoResult) { it -> it.networkErrors
    }

    /**
     * Search a repository based on a query string.
     */
    fun searchRepo() {
        queryLiveData.postValue(Unit)
    }

    /**
     * Factory for ViewModels
     */
    class ViewModelFactory(private val repository: GithubRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ReposListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ReposListViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
