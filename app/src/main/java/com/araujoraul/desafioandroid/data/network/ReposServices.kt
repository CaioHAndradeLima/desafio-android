package com.araujoraul.desafioandroid.data.network

import com.araujoraul.desafioandroid.data.model.PullRequests
import com.araujoraul.desafioandroid.data.model.Repositories
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ReposServices {

    @GET("search/repositories")
    fun getJavaRepositories(
            @Query("q") language: String = "language:Java",
            @Query("sort") sort: String = "stars",
            @Query("page") page: Int
    ): Call<Repositories>

    @GET("repos/{creator}/{repository}/pulls")
    fun getPullRequests(
            @Path("creator") creator: String,
            @Path("repository") repository: String
    ): Call<ArrayList<PullRequests>>

}