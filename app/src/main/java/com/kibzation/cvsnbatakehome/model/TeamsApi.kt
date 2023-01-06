package com.kibzation.cvsnbatakehome.model

import retrofit2.Response
import retrofit2.http.GET
interface TeamsApi {
    @GET("/api/v1/teams")
    suspend fun getTeams(): Response<TeamData>
}