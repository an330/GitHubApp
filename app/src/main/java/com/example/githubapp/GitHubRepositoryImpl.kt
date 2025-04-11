package com.example.githubapp

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(
    private val api: GitHubApiService,
    private val dao: RepoDao
) : GitHubRepository {

    override suspend fun getUserRepos(username: String): List<Repo> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = api.getUserRepos(username)
            if (response.isSuccessful) {
                val repos = response.body() ?: emptyList()
                val entities = repos.map { it.toEntity(username) }

                // Cache in Room
                dao.deleteReposByUser(username)
                dao.insertAll(entities)

                entities.map { it.toDomain() }
            } else {
                Log.w("GitHubRepository", "API error, loading from cache")
                dao.getReposByUser(username).map { it.toDomain() }
            }
        } catch (e: Exception) {
            Log.e("GitHubRepository", "Network failed, loading from cache", e)
            dao.getReposByUser(username).map { it.toDomain() }
        }
    }
}
