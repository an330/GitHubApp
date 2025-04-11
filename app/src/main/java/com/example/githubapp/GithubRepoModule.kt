package com.example.githubapp

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class GithubRepoModule {

    @Binds
    abstract fun bindGitHubRepository(
        impl: GitHubRepositoryImpl
    ): GitHubRepository
}
