
fun main () {
    val post = Post(can_edit = true,
        text = "Привет",
        likes = Likes(count = 5, can_like = true, user_likes = true, can_publish = true))
    println(post)

    val addedPost = WallService.add(post)
    println(addedPost)

    val updatedPost = addedPost.copy(text = "Updated post")
    val isUpdated = WallService.update(updatedPost)
    println(isUpdated)
}

data class Post (
    val id : Int = 0,
    val owner_id : Int = 0,
    val text : String = "текст записи",
    val from_id : Int = 0,
    val created_by : Int = 0,
    val reply_owner_id : Int = 0,
    val can_edit : Boolean = false,
    val is_pinned : Int = 0,
    val is_favorite : Boolean = false,
    val postponed_id : Int = 0,
    val likes : Likes = Likes()
)

data class Likes (
    val count : Int = 0,
    val user_likes : Boolean = false,
    val can_like : Boolean = false,
    val can_publish : Boolean = false

)

object WallService {
    private var posts: Array<Post?> = emptyArray()
    private var currentId: Int = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++currentId)
        return posts.last()!!
    }

    fun update(post: Post): Boolean {
        for ((index, existingPost) in posts.withIndex()) {
            if (existingPost?.id == post.id) {
                posts[index] = post
                return true
            }
        }
        return false
    }
}