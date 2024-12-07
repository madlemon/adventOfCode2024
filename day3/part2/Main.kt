package day3.part2

import java.io.File

private const val EXAMPLE = "day3/example.txt"
private const val INPUT = "day3/input.txt"

fun main() {
    val memory = File(INPUT).readLines().joinToString("")

    val mulRgx = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()
    val doRgx = "do\\(\\)".toRegex()
    val dontRgx = "don't\\(\\)".toRegex()

    val result = mulRgx.findAll(memory)
        .filter {
            val previousMemory = memory.substring(0, it.range.first)
            val doRangeStart = doRgx.findMostRecentOrNull(previousMemory)
            val dontRangeStart = dontRgx.findMostRecentOrNull(previousMemory) ?: return@filter true
            if (doRangeStart != null && doRangeStart > dontRangeStart) {
                return@filter true
            }
            return@filter false
        }
        .map { extractNumbers(it) }
        .map { it[0] * it[1] }
        .reduce { acc, next -> acc + next }

    println("Result: $result")
}

private fun extractNumbers(it: MatchResult) = it.value.drop(4)
    .dropLast(1)
    .split(",").map { num -> num.toInt() }

private fun Regex.findMostRecentOrNull(string: String): Int? {
    return this.findAll(string)
        .map { match -> match.range.first }
        .maxOrNull()
}




