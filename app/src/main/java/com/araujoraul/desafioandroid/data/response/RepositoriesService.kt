package com.araujoraul.desafioandroid.data.response

import android.util.Log
import com.araujoraul.desafioandroid.data.model.PullRequests
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

    @GET("repos/{creator}/{repository}/pulls")
    fun searchPullRequests(
            @Path("creator") creator: String,
            @Path("repository") repository: String
    ): Call<List<PullRequests>>

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

                    Log.d(TAG, "RESPONSE: $response")

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