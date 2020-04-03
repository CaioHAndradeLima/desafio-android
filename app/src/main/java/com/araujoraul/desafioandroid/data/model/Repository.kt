package com.araujoraul.desafioandroid.data.model

import com.google.gson.annotations.SerializedName

class Repository(
        @SerializedName("id")val id: Int = 0,
        @SerializedName("name")val name: String = "",
        @SerializedName("description")val description: String = "",
        @SerializedName("forks_count")val forksCount: Int = 0,
        @SerializedName("stargazers_count")val stargazersCount: Int = 0,
        @SerializedName("owner")val owner: Owner = Owner()
)