package com.araujoraul.desafioandroid.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "idOwner")
        @SerializedName("id") var id: Long,
        @SerializedName("repositoryId") var repositoryId: Long = 0,
        @SerializedName("avatar_url") var avatarUrl: String = "",
        @SerializedName("login") var username: String = ""
) : Parcelable