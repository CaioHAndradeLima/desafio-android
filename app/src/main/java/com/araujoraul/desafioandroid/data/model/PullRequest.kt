package com.araujoraul.desafioandroid.data.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "PullRequests")
data class PullRequest(
        @PrimaryKey(autoGenerate = true)
        @field:SerializedName("id") val id: Int,
        @field:SerializedName("body") val body: String?,
        @field:SerializedName("html_url") val htmlUrl: String?,
        @field:SerializedName("title") val title: String?,
        @field:SerializedName("url") val url: String,

        @field:SerializedName("created_at")
        @Embedded
        val createdAt: Date?,

        @SerializedName("user")
        @Embedded
        val user: User
) : Parcelable

