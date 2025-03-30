package exercise.ch4

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

class E03_CurryingTest {
    fun sum(num1: Int, num2: Int) = num1 + num2
    fun sum3(num1: Int, num2: Int, num3: Int) = num1 + num2 + num3
    fun sum4(num1: Int, num2: Int, num3: Int, num4: Int) = num1 + num2 + num3 + num4
    fun strConcat(s1: String, s2: String) = "$s1 $s2"
    fun strConcat3(prefix: String, middle: String, suffix: String) = "$prefix $middle $suffix"

    @Test
    fun `simple currying`() {
        val plus3Fn = ::sum.curry()(3)
        expectThat(plus3Fn(4)).isEqualTo(7)

        val starPrefixFn = ::strConcat.curry()("*")
        expectThat(starPrefixFn("abc")).isEqualTo("* abc")
    }

    @Test
    fun `simple currying2`() {
        val plus3Fn = ::sum.curry()
        expectThat(plus3Fn(3)(4)).isEqualTo(7)

        val starPrefixFn = ::strConcat.curry()
        expectThat(starPrefixFn("*")("abc")).isEqualTo("* abc")
    }

    @Test
    fun `simple currying3`() {
        val plus3Fn = ::sum.curry()(3)(4)
        expectThat(plus3Fn).isEqualTo(7)

        val starPrefixFn = ::strConcat.curry()("*")("abc")
        expectThat(starPrefixFn).isEqualTo("* abc")
    }

    @Test
    fun `infix partial application`() {
        val curriedConcat = ::strConcat.curry()
        expectThat(curriedConcat `+++` "head" `+++` "tail")
            .isEqualTo("head tail")
        val curriedSum = ::sum.curry()
        expectThat(curriedSum `+++` 4 `+++` 5).isEqualTo(9)
    }

    @Test
    fun `triple currying`() {
        val plus = ::sum3.curry()(3)
        expectThat(plus(4)(5)).isEqualTo(12)

        val str = ::strConcat3.curry()("Maeng")
        expectThat(str("CodeSpitz")("Study")).isEqualTo("Maeng CodeSpitz Study")
    }

    @Test
    fun `four currying`() {
        val plus = ::sum4.curry()(3)
        expectThat(plus(4)(5)(6)).isEqualTo(18)
    }
}
