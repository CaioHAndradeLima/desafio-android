package com.araujoraul.desafioandroid.data.model

import com.google.gson.annotations.SerializedName

class Repositories(
        @SerializedName("items")
        val items: ArrayList<Repository> = arrayListOf()
)