package day6.part1

import java.io.File

private const val EXAMPLE = "day6/example.txt"
private const val INPUT = "day6/input.txt"

fun main() {
    val map = File(INPUT).useLines { it.toMutableList() }.map { it.toCharArray() }.toMutableList()

    val up = Direction(-1, 0)
    val right = Direction(0, 1)
    val down = Direction(1, 0)
    val left = Direction(0, -1)

    fun rotate(currDirection: Direction): Direction {
        return when (currDirection) {
            up -> right
            right -> down
            down -> left
            left -> up
            else -> throw Exception("Invalid direction")
        }
    }

    var currPos = map.findStartingPosition()
    var currDirection = up
    println("Starting at Row:${currPos.row}, Col:${currPos.col}")

    while (true) {
        val (rowDelta, colDelta) = currDirection
        val nextPos = Position(currPos.row + rowDelta, currPos.col + colDelta)

        if (nextPos.isOutOfBounds(map)) {
            break
        } else if (map[nextPos.row][nextPos.col] == '#') {
            currDirection = rotate(currDirection);
        } else {
            map[nextPos.row][nextPos.col] = 'X'
            currPos = nextPos
        }
    }

    map.forEach { println(it) }
    println("Distinct positions: ${map.countDistinctPositions()}")
}

private data class Direction(val rowDelta: Int, val colDelta: Int)

private data class Position(val row: Int, val col: Int) {
    fun isOutOfBounds(map: List<CharArray>): Boolean =
        row !in map.indices || col !in map[row].indices
}

private typealias Map = List<CharArray>

private fun Map.findStartingPosition(): Position {
    for (row in indices) {
        for (col in this[row].indices) {
            if (this[row][col] == '^') {
                return Position(row, col)
            }
        }
    }
    throw IllegalArgumentException("No starting position found in the map!")
}

private fun Map.countDistinctPositions(): Int =
    sumOf { row -> row.count { it == 'X' } }





