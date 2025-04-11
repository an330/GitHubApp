package com.example.githubapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class RepoEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String?,
    val owner: String,
    val url: String

    )
