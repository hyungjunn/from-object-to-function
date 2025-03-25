## Exercise

#### 2.2
```kotlin
data class FunctionalStack<T>(private val elements: List<T> = emptyList()) {
    fun push(t: T): FunctionalStack<T> = FunctionalStack(listOf(t) + elements) // Be careful with the order.
    fun size(): Int = elements.size
    fun pop(): Pair<T, FunctionalStack<T>> = elements.first() to FunctionalStack(elements.drop(1)) // It can be confused with dropLast.
}
```
```kotlin
class FunctionalStackTest {
    //...
    
    @Test
    fun `test pop`() {
        val (b, stack) = FunctionalStack<Char>()
            .push('A')
            .push('B')
            .push('C')
            .pop()
        assertThat(stack.size()).isEqualTo(2)
        println(stack) // [B, A] when drop(1) <-> [C, B] when dropLast(1)
        assertThat(b).isEqualTo('C')
    }
}
```

#### 2.3
Difference between Java and Kotlin: 
- Java: use BiFunction
- Kotlin: Function return type
```kotlin
private val operatorMap: Map<Char, (Double, Double) -> Double> = mapOf(
    '+' to { a, b -> a + b },
    '-' to { a, b -> a - b },
    '*' to { a, b -> a * b },
    '/' to { a, b -> a / b }
)

private fun calculate(token: Token.Operator, o2: Double, o1: Double): Double {
    val op = operatorMap[token.operator] ?: error("Unknown operator: ${token.operator}")
    return op(o2, o1)
}

```
