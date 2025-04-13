import com.example.githubapp.Repo
import com.example.githubapp.NetworkDto

fun NetworkDto.toDomain(): Repo = Repo(
    id = id,
    name = name,
    description = description,
    url = htmlUrl
)