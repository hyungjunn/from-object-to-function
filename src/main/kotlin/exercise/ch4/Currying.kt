package exercise.ch4

fun <A, B, C> ((A, B) -> C).curry(): (A) -> (B) -> C = { a: A -> { b: B -> this(a, b) } }
infix fun <A, B> ((A) -> B).`+++`(a: A): B = this(a)
fun <A, B, C, D> ((A, B, C) -> D).curry(): (A) -> (B) -> (C) -> D = { a: A -> { b: B -> { c: C -> this(a, b, c) } } }
fun <A, B, C, D, E> ((A, B, C, D) -> E).curry(): (A) -> (B) -> (C) -> (D) -> E =
    { a: A -> { b: B -> { c: C -> { d: D -> this(a, b, c, d) } } } }
