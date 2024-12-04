package day1.part2

import java.io.File

private const val EXAMPLE = "day1/example.txt"
private const val INPUT = "day1/input.txt"

fun main() {
    val lines = File(INPUT).useLines { it.toList() }

    val locationIdListLeft = mutableListOf<Int>()
    val locationIdListRight = mutableListOf<Int>()

    lines.forEach {
        val split = it.trim().split("\\s+".toRegex())
        locationIdListLeft.add(split[0].toInt())
        locationIdListRight.add(split[1].toInt())
    }

    var sum = 0
    for (left in locationIdListLeft) {
        val appearances = locationIdListRight.count { it == left }
        sum += appearances * left
    }
    println("Result: $sum")
}

