package exercise.ch2

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class FunctionalStackTest {
    @Test
    fun push() {
        val stack1 = FunctionalStack<Char>()
        val stack2 = stack1.push('A')
        val stack3 = stack2.push('B')
        val stack4 = stack3.push('C')
        assertThat(stack1.size()).isEqualTo(0)
        assertThat(stack2.size()).isEqualTo(1)
        assertThat(stack3.size()).isEqualTo(2)
        assertThat(stack4.size()).isEqualTo(3)
    }
    @Test
    fun pop() {
        val (b, stack) = FunctionalStack<Char>()
            .push('A')
            .push('B')
            .push('C')
            .pop()
        assertThat(stack.size()).isEqualTo(2)
        println(b)
        println(stack) // [B, A] when drop(1) <-> [C, B] when dropLast(1)
        assertThat(b).isEqualTo('C')
    }
}
