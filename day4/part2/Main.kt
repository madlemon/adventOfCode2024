package day4.part2

import java.io.File

private const val EXAMPLE = "day4/example.txt"
private const val INPUT = "day4/input.txt"

fun main() {
    val lines = File(INPUT).useLines { it.toList() }
    val reports = lines.map { line ->
        line.trim()
            .split("\\s+".toRegex())
            .map(String::toInt)
    }
}





