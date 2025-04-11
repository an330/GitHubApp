package com.example.githubapp

import com.google.gson.annotations.SerializedName

data class RepoDto(
    val id: Int,
    val name: String,
    val description: String?,
    @SerializedName("html_url") val htmlUrl: String
)