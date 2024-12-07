package day4.part1

import java.io.File

private const val EXAMPLE = "day4/example.txt"
private const val INPUT = "day4/input.txt"

fun main() {
    val puzzle = File(INPUT).useLines { it.toList() }
    val word = "XMAS"
    val directions = listOf(
        0 to 1,   // Right
        0 to -1,  // Left
        1 to 0,   // Down
        -1 to 0,  // Up
        1 to 1,   // Diagonal Down-Right
        -1 to 1,  // Diagonal Up-Right
        1 to -1,  // Diagonal Down-Left
        -1 to -1  // Diagonal Up-Left
    )

    fun isWordAt(x: Int, dx: Int, y: Int, dy: Int): Boolean {
        for (i in 0..word.lastIndex) {
            val newX = x + i * dx
            val newY = y + i * dy
            val outOfBounds = newY !in puzzle.indices || newX !in puzzle[newY].indices
            if (outOfBounds || puzzle[newY][newX] != word[i]) {
                return false
            }
        }
        return true
    }

    val count = puzzle.indices.sumOf { y ->
        puzzle[y].indices.sumOf { x ->
            directions.count { (dx, dy) -> isWordAt(x, dx, y, dy) }
        }
    }

    println(count)
}

