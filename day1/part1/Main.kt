package day1.part1

import java.io.File

private const val EXAMPLE = "./day1/part1/example.txt"
private const val INPUT = "./day1/part1/input.txt"

fun main() {
    val lines = File(INPUT).useLines { it.toList() }

    val locationIdListLeft = mutableListOf<Int>()
    val locationIdListRight = mutableListOf<Int>()


    lines.forEach {
        val split = it.trim().split("\\s+".toRegex())
        locationIdListLeft.add(split[0].toInt())
        locationIdListRight.add(split[1].toInt())
    }

    locationIdListLeft.sort()
    locationIdListRight.sort()


    val distances = locationIdListLeft.zip(locationIdListRight) { idl, idr -> distance(idl, idr) }

    println("Result: " + distances.sum())

}

fun distance(a: Int, b: Int): Int {
    if (a > b) {
        return a - b
    } else if (a < b) {
        return b - a
    }
    return 0
}
