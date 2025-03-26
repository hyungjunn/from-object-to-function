package exercise.ch3

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class Ex03_FunAsResultTest {
    @Test
    fun `char at pos function builder`() {
        val myCharAtPosKotlin = buildCharAtPos("Kotlin")
        expectThat(myCharAtPosKotlin(0)).isEqualTo('K')

        val myCharAtPosPragProg = buildCharAtPos("PragProg")
        expectThat(myCharAtPosPragProg(5)).isEqualTo('r')
    }

    private fun buildCharAtPos(s: String): (Int) -> Char = { i -> s[i] }
}
