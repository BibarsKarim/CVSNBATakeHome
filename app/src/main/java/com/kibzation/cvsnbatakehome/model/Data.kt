package com.kibzation.cvsnbatakehome.model

import com.google.gson.annotations.SerializedName

data class TeamData(
    @SerializedName("data")
    val teams: List<Team>
)
