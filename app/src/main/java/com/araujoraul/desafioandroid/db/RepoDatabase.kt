package com.araujoraul.desafioandroid.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.araujoraul.desafioandroid.data.model.PullRequest
import com.araujoraul.desafioandroid.data.model.Repository

@Database(
        entities = [Repository::class, PullRequest::class],
        version = 1,
        exportSchema = false
)
abstract class RepoDatabase : RoomDatabase(){

    abstract fun reposDao(): RepoDao

    companion object{

        @Volatile
        private var INSTANCE: RepoDatabase? = null

       fun getInstance(context: Context): RepoDatabase =
               INSTANCE ?: synchronized(this){
                   INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
               }


        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        RepoDatabase::class.java, "GithubJava.db")
                        .build()

    }

}