package com.araujoraul.desafioandroid.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

class PullRequests(
        @SerializedName("body")
        val body: String = "",
        @SerializedName("created_at")
        val createdAt: Date = Date(),
        @SerializedName("html_url")
        val htmlUrl: String = "",
        @SerializedName("title")
        val title: String = "",
        @SerializedName("url")
        val url: String = "",
        @SerializedName("user")
        val user: User = User()
)