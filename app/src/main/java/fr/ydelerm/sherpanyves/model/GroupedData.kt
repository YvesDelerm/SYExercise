package fr.ydelerm.sherpanyves.model

data class GroupedData(
    val users: List<User>,
    val posts: List<Post>,
    val albums: List<Album>,
    val photos: List<Photo>
)
