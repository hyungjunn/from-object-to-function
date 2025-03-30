package exercise.ch4

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class Ex04_InvokableTest {
    @Test
    fun `generate email text`() {
        val limHyungJunn = Person("Lim", "Hyungjunn")
        val emailTemplate = EmailTemplate("Hi I'm {firstName} {lastName}")
        expectThat(emailTemplate(limHyungJunn)).isEqualTo("Hi I'm Lim Hyungjunn")
    }
}
