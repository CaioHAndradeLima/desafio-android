package com.araujoraul.desafioandroid.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "PullRequests")
data class PullRequest(
        @PrimaryKey(autoGenerate = true)
        @field:SerializedName("id") val id: Int,
        @field:SerializedName("body") val body: String?,
        @field:SerializedName("html_url") val htmlUrl: String?,
        @field:SerializedName("title") val title: String?,
        @field:SerializedName("url") val url: String
//        @field:SerializedName("created_at") val createdAt: Date = Date(),
//        @SerializedName("user") val user: List<User> = emptyList()
)