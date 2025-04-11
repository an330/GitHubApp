
package com.example.githubapp


fun RepoDto.toEntity(owner: String) = RepoEntity(
    id = id,
    name = name,
    description = description,
    owner = owner,
    url = htmlUrl
)

fun RepoEntity.toDomain() = Repo(
    id = id,
    name = name,
    description = description,
    url = url
)

// From domain model to Room entity
// We need the 'owner' (GitHub username) as an input

