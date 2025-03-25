package ch2

data class FunctionalStack<T>(private val elements: List<T> = emptyList()) {
    fun push(t: T): FunctionalStack<T> = FunctionalStack(listOf(t) + elements)
    fun size(): Int = elements.size
    fun pop(): Pair<T, FunctionalStack<T>> = elements.first() to FunctionalStack(elements.drop(1))
}
