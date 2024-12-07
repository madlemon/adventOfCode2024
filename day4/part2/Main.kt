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

    fun isWordAt(x: Int, dx: Int, y: Int, dy: Int): Boolean {
        for (i in 0..word.lastIndex) {
            val offset = word.centerIndex()
            val newX = x + (i - offset) * dx
            val newY = y + (i - offset) * dy
            val outOfBounds = newY !in puzzle.indices || newX !in puzzle[newY].indices
            if (outOfBounds || puzzle[newY][newX] != word[i]) {
                return false
            }
        }
        return true
    }

    var count = 0
    for (y in puzzle.indices) {
        for (x in puzzle[y].indices) {
            if (puzzle[y][x] == word.centerChar() &&
                diagonalDirections.count { (dx, dy) -> isWordAt(x, dx, y, dy) } >= 2
            ) {
                count++
            }
        }
    }
    println(count)
}

private fun String.centerIndex(): Int = (this.length - 1) / 2
private fun String.centerChar(): Char = this[centerIndex()]






