package com.araujoraul.desafioandroid.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Repositories")
data class Repository(
        @PrimaryKey @field:SerializedName("id")val id: Long,
        @field:SerializedName("name")val name: String?,
        @field:SerializedName("description")val description: String?,
        @field:SerializedName("forks_count")val forks: Int = 0,
        @field:SerializedName("stargazers_count")val stars: Int = 0
//        @field:SerializedName("owner")val owner: Owner = null
)