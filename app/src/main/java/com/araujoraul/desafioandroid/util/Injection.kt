package com.araujoraul.desafioandroid.util

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.araujoraul.desafioandroid.data.api.ApiService
import com.araujoraul.desafioandroid.data.api.GithubRepository
import com.araujoraul.desafioandroid.db.GithubLocalCache
import com.araujoraul.desafioandroid.db.RepoDatabase
import com.araujoraul.desafioandroid.presentation.ui.reposlist.ViewModelFactory
import java.util.concurrent.Executors

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {

    /**
     * Creates an instance of [GithubLocalCache] based on the database DAO.
     */
    private fun provideCache(context: Context): GithubLocalCache {
        val database = RepoDatabase.getInstance(context)
        return GithubLocalCache(database.reposDao(), Executors.newSingleThreadExecutor())
    }

    /**
     * Creates an instance of [GithubRepository] based on the [GithubService] and a
     * [GithubLocalCache]
     */
    private fun provideGithubRepository(context: Context): GithubRepository {
        return GithubRepository(ApiService.create(), provideCache(context))
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository(context))
    }


}