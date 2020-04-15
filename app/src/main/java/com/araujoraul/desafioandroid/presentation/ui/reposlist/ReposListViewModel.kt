package com.araujoraul.desafioandroid.presentation.ui.reposlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.araujoraul.desafioandroid.data.api.GithubRepository
import com.araujoraul.desafioandroid.data.model.RepositoriesResult
import com.araujoraul.desafioandroid.data.model.Repository

class ReposListViewModel(private val repository: GithubRepository) : ViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    private val queryLiveData = MutableLiveData<String>()
    private val repoResult: LiveData<RepositoriesResult> = Transformations.map(queryLiveData) {
        repository.search(it)
    }

    val repos: LiveData<List<Repository>> = Transformations.switchMap(repoResult) { it -> it.data }
    val networkErrors: LiveData<String> = Transformations.switchMap(repoResult) { it ->
        it.networkErrors
    }

    /**
     * Search a repository based on a query string.
     */
    fun searchRepo(queryString: String) {
        queryLiveData.postValue(queryString)
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            val immutableQuery = lastQueryValue()
            if (immutableQuery != null) {
                repository.requestMore(immutableQuery)
            }
        }
    }

    /**
     * Get the last query value.
     */
    fun lastQueryValue(): String? = queryLiveData.value
}
