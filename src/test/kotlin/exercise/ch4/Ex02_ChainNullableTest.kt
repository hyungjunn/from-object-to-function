package exercise.ch4

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isEqualTo
import java.net.URI

class Ex02_ChainNullableTest {
    val processUnlessNull = ::extractListData andUnlessNull
            ::fetchListContent andUnlessNull
            ::renderHtml andUnlessNull
            ::createResponse

    fun fetchList(request: Request): Response = processUnlessNull(request)
        ?: Response(404, "Not found")

    @Test
    fun `chain nullable`() {
        val response = fetchList(Request("GET", URI("http://example.com/zettai/uberto/mylist"), ""))
        expectThat(response) {
            get { status }.isEqualTo(200)
            get { body }.contains("<td>uberto buy milk</td>")
            get { body }.contains("<td>complete mylist</td>")
            get { body }.contains("<td>something else</td>")
        }
    }

    @Test
    fun `wrong request`() {
        val response = fetchList(Request("GET", URI("http://codespitz.com"), ""))
        expectThat(response) {
            get { status }.isEqualTo(404)
            get { body }.isEqualTo("Not found")
        }
    }
}

fun extractListData(request: Request): Pair<User, ListName>? {
    val frag = request.uri.path.split('/')

    return if (frag.size != 4)
        null
    else {
        val user = frag[2]
        val list = frag[3]
        user to list
    }
}

fun fetchListContent(listId: Pair<User, ListName>): ToDoList? =
    if (listId.first.length < 3)
        null
    else
        listOf(
            "${listId.first} buy milk",
            "complete ${listId.second}",
            "something else"
        )

fun renderHtml(todoList: ToDoList): Html =
    """
    <html>
        <body>
            <h1>Zettai</h1>
            <table>
                <tbody>${renderItems(todoList)}</tbody>
            </table>
        </body>
    </html>
    """.trimIndent()

private fun renderItems(items: List<String>) =
    items.map {
        """<tr><td>${it}</td></tr>""".trimIndent()
    }.joinToString("\n")

fun createResponse(html: Html): Response =
    Response(200, html)

typealias User = String
typealias ListName = String
typealias ToDoList = List<String>
typealias Html = String

data class Request(
    val method: String,
    val uri: URI,
    val body: String
)

data class Response(
    val status: Int,
    val body: String
)


