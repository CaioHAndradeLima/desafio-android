package com.araujoraul.desafioandroid.presentation.ui.pullrequests

import com.araujoraul.desafioandroid.data.api.GithubRepository
import com.araujoraul.desafioandroid.data.model.PullRequest
import java.util.concurrent.Executors


class PullRequestRepository(private val repository: GithubRepository) {

    fun request(owner: String ,repo: String): List<PullRequest> {
        repository.requestPullsAndSaveData(owner,repo)

        return repository.searchPulls()
    }
}