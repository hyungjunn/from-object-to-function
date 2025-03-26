package exercise.ch2

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class E03_RpnCalculatorTest {
    @Test
    fun `simple sum`() {
        val res = RpnCalculator.calc("4 5 +")
        assertThat(res).isEqualTo(9.0)
    }
    @Test
    fun `simple divide`() {
        val res = RpnCalculator.calc("6 2 /")
        assertThat(res).isEqualTo(3.0)
    }
    @Test
    fun `multiple operations`() {
        val res = RpnCalculator.calc("5 6 2 1 + / *")
        assertThat(res).isEqualTo(10.0)
    }
    @Test
    fun `complex operations`() {
        val res = RpnCalculator.calc("2 5 * 4 + 3 2 * 1 + /")
        assertThat(res).isEqualTo(2.0)
    }
}
