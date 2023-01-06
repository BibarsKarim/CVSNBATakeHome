package com.kibzation.cvsnbatakehome.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TeamsService {
    private val BASE_URL = "https://www.balldontlie.io"
    fun getTeamService(): TeamsApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TeamsApi::class.java)
    }
}