package com.araujoraul.desafioandroid.data.model

import androidx.lifecycle.LiveData
import com.google.gson.annotations.SerializedName

data class RepositoriesResponse(
        @SerializedName("total_count") val total: Int = 0,
        @SerializedName("items") val items: List<Repository> = emptyList(),
        val nextPage: Int? = null
)

data class RepositoriesResult(
        val data: LiveData<List<Repository>>,
        val networkErrors: LiveData<String>
)