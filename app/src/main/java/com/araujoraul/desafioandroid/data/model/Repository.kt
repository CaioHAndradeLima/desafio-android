package com.araujoraul.desafioandroid.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Repositories")
data class Repository(
        @PrimaryKey(autoGenerate = true)
        @field:SerializedName("id")val id: Long,
        @field:SerializedName("name")val name: String?,
        @field:SerializedName("description")val description: String?,
        @field:SerializedName("forks_count")val forks: Int = 0,
        @field:SerializedName("stargazers_count")val stars: Int = 0
//        @SerializedName("owner")val owner: List<Owner> = emptyList()
)
