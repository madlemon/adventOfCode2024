package day3.part1

import java.io.File

private const val EXAMPLE = "day3/example.txt"
private const val INPUT = "day3/input.txt"

fun main() {
    val memory = File(INPUT).useLines { it.toList() }
        .reduce { acc, next -> acc + next }

    val rgx = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()

    val result = rgx.findAll(memory)
        .map { extractNumbers(it) }
        .map { it[0] * it[1] }
        .reduce { acc, next -> acc + next }

    println("Result: $result")

}

private fun extractNumbers(it: MatchResult) = it.value.drop(4)
    .dropLast(1)
    .split(",").map { num -> num.toInt() }
