package com.example.githubapp

interface GitHubRepository {
    suspend fun getUserRepos(username: String): List<Repo>
}
