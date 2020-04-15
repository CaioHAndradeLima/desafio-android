package com.araujoraul.desafioandroid.data.model

import com.google.gson.annotations.SerializedName

data class Owner(
        @SerializedName("id")val id: Int = 0,
        @SerializedName("avatar_url")val avatarUrl: String = "",
        @SerializedName("login")val login: String = ""
)