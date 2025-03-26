package exercise.ch4

data class ToDoItem(
    val description: String,
    val status: ToDoStatus = ToDoStatus.Todo
)

enum class ToDoStatus {Todo, InProgress, Done, Blocked}

fun <T> T.discardUnless(predicate: T.() -> Boolean): T? =
    if (predicate(this)) this else null
