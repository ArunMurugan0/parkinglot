package utils

object Counter {
    private var counter: Long = 0

    fun next(): Long {
        return ++counter
    }
}