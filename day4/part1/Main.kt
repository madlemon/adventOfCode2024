package day4.part1

import java.io.File

private const val EXAMPLE = "day4/example.txt"
private const val INPUT = "day4/input.txt"

fun main() {
    val puzzle = File(INPUT).useLines { it.toList() }
    val word = "XMAS"

    var count = 0;

    for ((y, _) in puzzle.withIndex()) {
        for ((x, _) in puzzle[0].withIndex()) {
            for (i in 0..word.lastIndex) {
                if (x + i > puzzle[x].lastIndex || puzzle[y][x + i] != word[i]) {
                    break
                }
                if (i == word.lastIndex) {
                    count++
                }
            }

            for (i in 0..word.lastIndex) {
                if (x - i < 0 || puzzle[y][x - i] != word[i]) {
                    break
                }
                if (i == word.lastIndex) {
                    count++
                }
            }

            for (i in 0..word.lastIndex) {
                if (y + i > puzzle[y].lastIndex || puzzle[y + i][x] != word[i]) {
                    break
                }
                if (i == word.lastIndex) {
                    count++
                }
            }

            for (i in 0..word.lastIndex) {
                if (y - i < 0 || puzzle[y - i][x] != word[i]) {
                    break
                }
                if (i == word.lastIndex) {
                    count++
                }
            }

            for (i in 0..word.lastIndex) {
                if (x + i > puzzle[x].lastIndex || y + i > puzzle[y].lastIndex || puzzle[y + i][x + i] != word[i]) {
                    break
                }
                if (i == word.lastIndex) {
                    count++
                }
            }

            for (i in 0..word.lastIndex) {
                if (x + i > puzzle[x].lastIndex || y - i < 0 || puzzle[y - i][x + i] != word[i]) {
                    break
                }
                if (i == word.lastIndex) {
                    count++
                }
            }

            for (i in 0..word.lastIndex) {
                if (x - i < 0 || y + i > puzzle[y].lastIndex || puzzle[y + i][x - i] != word[i]) {
                    break
                }
                if (i == word.lastIndex) {
                    count++
                }
            }

            for (i in 0..word.lastIndex) {
                if (x - i < 0 || y - i < 0 || puzzle[y - i][x - i] != word[i]) {
                    break
                }
                if (i == word.lastIndex) {
                    count++
                }
            }
        }
    }

    println(count)
}

