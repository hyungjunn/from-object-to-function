package exercise.ch2

object RpnCalculator {
    fun calc(expression: String): Double {
        val initStack = FunctionalStack<Double>()
        val finalStack = parse(expression).fold(initStack) { stack, token ->
            apply(token, stack)
        }
        return finalStack.pop().first
    }
    private fun apply(token: Token, stack: FunctionalStack<Double>): FunctionalStack<Double> {
        return when (token) {
            is Token.Number -> stack.push(token.value)
            is Token.Operator -> {
                val (o1, stack1) = stack.pop()
                val (o2, stack2) = stack1.pop()
                val result = evaluate(token, o2, o1)
                stack2.push(result)
            }
        }
    }
    private fun parse(expr: String): List<Token> {
        return expr.split(" ")
            .map { token ->
                val asNumber = token.toDoubleOrNull()
                if (asNumber != null) {
                    Token.Number(asNumber)
                } else {
                    Token.Operator(token[0])
                }
            }
    }
    private fun evaluate(token: Token.Operator, o2: Double, o1: Double): Double {
        val op = operatorMap[token.operator] ?: error("Unknown operator: ${token.operator}")
        return op(o2, o1)
    }
    private val operatorMap: Map<Char, (Double, Double) -> Double> = mapOf(
        '+' to { a, b -> a + b },
        '-' to { a, b -> a - b },
        '*' to { a, b -> a * b },
        '/' to { a, b -> a / b }
    )
    sealed class Token {
        data class Number(val value: Double) : Token()
        data class Operator(val operator: Char) : Token()
    }
}
