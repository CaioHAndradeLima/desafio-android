package com.araujoraul.desafioandroid.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class User(
        @SerializedName("avatar_url")val avatarUrl: String = "",
        @SerializedName("login")val login: String = ""
) : Parcelable
