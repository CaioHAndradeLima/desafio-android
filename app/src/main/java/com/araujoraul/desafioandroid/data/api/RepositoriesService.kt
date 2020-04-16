package com.araujoraul.desafioandroid.data.api

import android.util.Log
import com.araujoraul.desafioandroid.data.model.PullRequest
import com.araujoraul.desafioandroid.data.model.RepositoriesResponse
import com.araujoraul.desafioandroid.data.model.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val TAG = "GithubService"
private const val IN_QUALIFIER = "in:name,description"

interface RepositoriesService {

    @GET("search/repositories?q=language:java&sort=stars")
    fun searchJavaPopRepositories(
            @Query("q") query: String,
            @Query("page") page: Int,
            @Query("per_page") itemsPerPage: Int
    ): Call<RepositoriesResponse>

    @GET("repos/{owner}/{repo}/pulls")
    fun searchPullRequests(
            @Path("owner") owner: String,
            @Path("repo") repo: String
    ): Call<List<PullRequest>>

}

fun searchJavaPopRepositories(
        service: RepositoriesService,
        query: String,
        page: Int,
        itemsPerPage: Int,
        onSuccess: (repos: List<Repository>) -> Unit,
        onError: (error: String) -> Unit
) {
    Log.d(TAG, "query: $query, page: $page, itemsPerPage: $itemsPerPage")

    val apiQuery = query + IN_QUALIFIER

    service.searchJavaPopRepositories(apiQuery, page, itemsPerPage).enqueue(
            object : Callback<RepositoriesResponse>{

                override fun onResponse(call: Call<RepositoriesResponse>, response: Response<RepositoriesResponse>) {

                    Log.d(TAG, "RESPONSE REPOSITORIES: $response")

                    if (response.isSuccessful){
                        val repositories = response.body()?.items ?: emptyList()
                        onSuccess(repositories)

                    } else{
                        onError(response.errorBody()?.string() ?: "Unknown error")
                    }
                }

                override fun onFailure(call: Call<RepositoriesResponse>, t: Throwable) {
                    Log.d(TAG, "Fail to get data")
                    onError(t.message ?: "Unknown error")
                }

            }
    )

}

fun searchPullRequests(
        service: RepositoriesService,
        owner: String,
        repo: String,
        onSuccess: (pulls: List<PullRequest>) -> Unit,
        onError: (error: String) -> Unit
){
    Log.d(TAG, "owner: $owner, repo: $repo")

    service.searchPullRequests(owner, repo).enqueue(
            object : Callback<List<PullRequest>>{

                override fun onResponse(call: Call<List<PullRequest>>, response: Response<List<PullRequest>>) {

                    Log.d(TAG, "RESPONSE PULLREQUEST: $response")

                    if (response.isSuccessful){
                        val pulls = response.body() ?: listOf()
                        onSuccess(pulls)

                    } else{
                        onError(response.errorBody()?.string() ?: "Unknown error")
                    }


                    TODO("Verify if it's ok")
                }

                override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                    Log.d(TAG, "Fail to get data")
                    onError(t.message ?: "Unknown error")
                }

            }
    )

}