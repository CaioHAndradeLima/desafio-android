package com.araujoraul.desafioandroid.data.response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    const val BASE_URL = "https://api.github.com/"
    private var INSTANCE: ReposServices? = null

    val instance: ReposServices?
    get() {
        if (INSTANCE == null){
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            INSTANCE = retrofit.create(ReposServices::class.java)
        }
        return INSTANCE
    }
}