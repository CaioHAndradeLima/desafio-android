package com.araujoraul.desafioandroid.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.araujoraul.desafioandroid.data.model.PullRequest
import com.araujoraul.desafioandroid.data.model.Repository

@Dao
interface RepoDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepos(posts: List<Repository>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPulls(posts: List<PullRequest>)

    //for a specific search result
    @Query("SELECT * FROM Repositories")
    fun searchAllRepositories(): LiveData<List<Repository>>

    @Query("SELECT * FROM PullRequests")
    fun searchAllPull() : List<PullRequest>


}