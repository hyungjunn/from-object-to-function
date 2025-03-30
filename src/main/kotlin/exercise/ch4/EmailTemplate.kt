package exercise.ch4

import exercise.ch3.StringTag
import exercise.ch3.renderTemplate

class EmailTemplate(private val templateText: String): (Person) -> String {
    override fun invoke(aPerson: Person): String =
        renderTemplate(
            templateText, mapOf(
                "firstName" to StringTag(aPerson.firstName),
                "lastName" to StringTag(aPerson.lastName)
            )
        )
}

data class Person(
    val firstName: String,
    val lastName: String,
)
