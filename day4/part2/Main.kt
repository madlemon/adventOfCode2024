package day4.part2

import java.io.File

private const val EXAMPLE = "day4/example.txt"
private const val INPUT = "day4/input.txt"

fun main() {
    val puzzle = File(INPUT).useLines { it.toList() }
    val word = "MAS"

    require(word.length % 2 != 0) { "Word length must be odd." }

    val diagonalDirections = listOf(
        1 to 1,   // Diagonal Down-Right
        -1 to 1,  // Diagonal Up-Right
        1 to -1,  // Diagonal Down-Left
        -1 to -1  // Diagonal Up-Left
    )

    fun isWordAt(x: Int, y: Int, direction: Vector): Boolean {
        for (i in 0..word.lastIndex) {
            val offset = word.centerIndex()
            val newX = x + (i - offset) * direction.x
            val newY = y + (i - offset) * direction.y
            val outOfBounds = newY !in puzzle.indices || newX !in puzzle[newY].indices
            if (outOfBounds || puzzle[newY][newX] != word[i]) {
                return false
            }
        }
        return true
    }

    val count = puzzle.indices.sumOf { y ->
        puzzle[y].indices.sumOf { x ->
            if (puzzle[y][x] == word.centerChar()
                && diagonalDirections.count { isWordAt(x, y, it) } >= 2
            )
                1L else 0
        }
    }
    println(count)
}

private fun String.centerIndex(): Int = (this.length - 1) / 2
private fun String.centerChar(): Char = this[centerIndex()]

private typealias Vector = Pair<Int, Int>

val Vector.y: Int
    get() = this.first

val Vector.x: Int
    get() = this.second






