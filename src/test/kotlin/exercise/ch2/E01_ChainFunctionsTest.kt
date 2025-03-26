package exercise.ch2

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class E01_ChainFunctionsTest {
    @Test
    fun `integer function composition`() {
        val double: FUN<Int, Int> = { it * 2 }
        val increment: FUN<Int, Int> = { it + 1 }
        val doubleAndThenIncrement = double andThen increment
        assertThat(doubleAndThenIncrement(2)).isEqualTo(5)
    }
    @Test
    fun `string has odd length`() {
        val length: FUN<String, Int> = { it.length }
        val isOdd: FUN<Int, Boolean> = { it % 2 != 0 }
        val hasOddLength = length andThen isOdd
        assertThat(hasOddLength("1234")).isEqualTo(false)
        assertThat(hasOddLength("12345")).isEqualTo(true)
        assertThat(hasOddLength("123456")).isEqualTo(false)
    }
    @Test
    fun `string transform`() {
        val removeSpace: FUN<String, String> = { it.replace(" ", "") }
        val toLowerCase: FUN<String, String> = { it.lowercase() }
        val reverse: FUN<String, String> = { it.reversed() }
        val transform = removeSpace andThen toLowerCase andThen reverse
        assertThat(transform("CODE SPITZ")).isEqualTo("ztipsedoc")
    }
}
