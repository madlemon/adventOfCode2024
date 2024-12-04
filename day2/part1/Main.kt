package day2.part1

import java.io.File

private const val EXAMPLE = "day2/example.txt"
private const val INPUT = "day2/input.txt"

fun main() {
    val lines = File(INPUT).useLines { it.toList() }
    val reports = mutableListOf<List<Int>>()

    lines.forEach { line ->
        val levels = line.trim()
            .split("\\s+".toRegex())
            .map {
                it.trim().toInt()
            }
        reports.add(levels)
    }

    var numOfSafeReports = 0
    for (report in reports) {
        var increasing = false
        var safe = true
        for ((index, level) in report.withIndex()) {
            if (index != report.lastIndex && safe) {
                val nextLevel = report[index + 1]
                if (level < nextLevel) {
                    if (!increasing && index != 0) {
                        safe = false
                        println("Suddenly increasing: $report")
                    }
                    increasing = true
                    if (nextLevel - level > 3) {
                        safe = false
                        println("More than 3: $report, $level, $nextLevel")
                    }
                } else if (level > nextLevel) {
                    if (increasing) {
                        safe = false
                        println("Suddenly decreasing: $report")
                    }
                    increasing = false
                    if (level - nextLevel > 3) {
                        safe = false
                        println("More than 3: $report, $level, $nextLevel")
                    }
                } else {
                    safe = false;
                }
            }
        }
        if (safe) {
            numOfSafeReports++
            println("Safe report: $report")
        }
    }

    println("Number of safe reports: $numOfSafeReports")


}
