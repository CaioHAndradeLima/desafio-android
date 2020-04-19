package com.araujoraul.desafioandroid.util

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.data.api.ApiService
import com.araujoraul.desafioandroid.data.api.GithubRepository
import com.araujoraul.desafioandroid.db.GithubLocalCache
import com.araujoraul.desafioandroid.db.RepoDatabase
import com.araujoraul.desafioandroid.presentation.ui.pullrequests.PullRequestsViewModel
import com.araujoraul.desafioandroid.presentation.ui.reposlist.ReposListViewModel
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
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
     * Creates an instance of [GithubRepository] based on the [ApiService] and a
     * [GithubLocalCache]
     */
    private fun provideGithubRepository(context: Context): GithubRepository {
        return GithubRepository(ApiService.create(), provideCache(context))
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideReposViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ReposListViewModel.ViewModelFactory(provideGithubRepository(context))
    }


    fun providePullsViewModelFactory(context: Context, owner: String, repo: String): ViewModelProvider.Factory {
        return PullRequestsViewModel.ViewModelFactory(provideGithubRepository(context), owner, repo)
    }

}

