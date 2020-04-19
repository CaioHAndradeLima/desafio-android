package com.araujoraul.desafioandroid.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Repositories")
data class Repository(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "idRepository")
        @field:SerializedName("id") var id: Long = 0,
        @field:SerializedName("name") var name: String? = "",
        @field:SerializedName("description") var description: String? = "",
        @field:SerializedName("forks_count") var forks: Int = 0,
        @field:SerializedName("stargazers_count") var stars: Int = 0,

        @field:SerializedName("owner")
        @Embedded
        var owner: Owner
) : Parcelable