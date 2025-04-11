import com.example.githubapp.Repo
import com.example.githubapp.RepoDto

fun RepoDto.toDomain(): Repo = Repo(
    id = id,
    name = name,
    description = description,
    url = htmlUrl
)