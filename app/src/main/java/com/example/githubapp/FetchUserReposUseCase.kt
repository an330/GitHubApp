package com.example.githubapp

import javax.inject.Inject

class FetchUserReposUseCase @Inject constructor(
    private val repository: GitHubRepository
) {
    suspend operator fun invoke(username: String): List<Repo> {
        return repository.getUserRepos(username)
    }
}
