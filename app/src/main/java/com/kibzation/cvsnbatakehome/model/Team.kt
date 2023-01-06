package com.kibzation.cvsnbatakehome.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("id")
    val id: Int,
    @SerializedName("abbreviation")
    val abbreviation: String,
    @SerializedName("conference")
    val conference: String,
    @SerializedName("division")
    val division: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("city")
    val city: String

) {
    override fun toString(): String {
        return name
    }
}
