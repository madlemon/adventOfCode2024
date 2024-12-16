package day6.part2

import java.io.File

private const val EXAMPLE = "day6/example.txt"
private const val INPUT = "day6/input.txt"

fun main() {
    val map = File(INPUT).useLines { it.toMutableList() }.map { it.toCharArray() }.toMutableList()
    val startPos = map.findStartingPosition()
    val result = countDistinctLoops(map, startPos)


    println("Distinct paths that loop: $result")
}

private fun countDistinctLoops(map: MutableList<CharArray>, startPos: Position): Int {
    var result = 0
    for (row in map.indices) {
        for (col in map[row].indices) {
            if (startPos.row == row && startPos.col == col) continue // skip starting positions
            val newMap = map.deepCopy()
            newMap[row][col] = 'O'
            if (isALoop(newMap, startPos)) result++
        }
    }
    return result
}


private fun isALoop(map: MutableList<CharArray>, startPos: Position): Boolean {
    var currPos = startPos
    var currDirection = up

    while (true) {
        val (rowDelta, colDelta) = currDirection
        val nextPos = Position(currPos.row + rowDelta, currPos.col + colDelta)

        if (nextPos.isOutOfBounds(map)) {
            return false
        } else if (map[nextPos.row][nextPos.col] == '#' || map[nextPos.row][nextPos.col] == 'O') {
            currDirection = currDirection.rotateClockwise()
        } else {
            val c = map[nextPos.row][nextPos.col]
            if (c == '.') {
                map[nextPos.row][nextPos.col] = '1'
            } else if (c.isDigit()) {
                var numOfVisits = c.digitToInt()
                if (numOfVisits == 4) {
                    map.forEach { println(it) }
                    println("----------------------")
                    return true
                } else {
                    numOfVisits++
                    map[nextPos.row][nextPos.col] = numOfVisits.digitToChar()
                }
            }
            currPos = nextPos
        }
    }

}

private data class Direction(val rowDelta: Int, val colDelta: Int)

private val up = Direction(-1, 0)
private val right = Direction(0, 1)
private val down = Direction(1, 0)
private val left = Direction(0, -1)

private fun Direction.rotateClockwise(): Direction {
    return when (this) {
        up -> right
        right -> down
        down -> left
        left -> up
        else -> throw Exception("Invalid direction")
    }
}

private data class Position(val row: Int, val col: Int) {
    fun isOutOfBounds(map: List<CharArray>): Boolean =
        row !in map.indices || col !in map[row].indices
}

private fun Position.move(direction: Direction): Position =
    Position(this.row + direction.rowDelta, this.col + direction.colDelta)

private typealias Map = MutableList<CharArray>

private fun Map.deepCopy(): Map =
    this.map { it.copyOf() }.toMutableList()

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
