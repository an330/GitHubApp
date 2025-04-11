package com.example.githubapp

data class Repo(
    val id: Int,
    val name: String,
    val description: String?,
    val url: String
)

/*fun RepoDto.toDomain(): Repo {
    return Repo(
        id = id,
        name = name,
        description = description,
        url = htmlUrl
    )
}*/
