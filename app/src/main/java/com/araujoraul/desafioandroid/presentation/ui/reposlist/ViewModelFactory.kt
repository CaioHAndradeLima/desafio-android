package com.araujoraul.desafioandroid.presentation.ui.reposlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.araujoraul.desafioandroid.data.api.GithubRepository

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