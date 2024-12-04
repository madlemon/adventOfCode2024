package day2.part2

import java.io.File

private const val EXAMPLE = "day2/example.txt"
private const val INPUT = "day2/input.txt"

fun main() {
    val lines = File(INPUT).useLines { it.toList() }
    val reports = lines.map { line ->
        line.trim()
            .split("\\s+".toRegex())
            .map(String::toInt)
    }

    val numOfSafeReports = reports.count { report ->
        when (val unsafeIndex = validate(report)) {
            null -> true // Report is safe
            else -> listOf(unsafeIndex, unsafeIndex + 1, unsafeIndex - 1)
                .filter { it in report.indices }
                .map { report.toMutableList().apply { removeAt(it) } }
                .any { validate(it) == null }
        }
    }

    println("Number of safe reports: $numOfSafeReports")


}

/**
 * Validates the levels of the report.
 *
 * If the report is safe null is returned. If the report is unsafe the potential problematic index is returned.
 */
private fun validate(report: List<Int>): Int? {
    var increasing = false

    for (index in 0 until report.lastIndex) {
        val current = report[index]
        val next = report[index + 1]
        if (current < next) {
            if (!increasing && index != 0) {
                println("Suddenly increasing: $report")
                return index
            }
            increasing = true
            if (next - current > 3) {
                println("More than 3: $report, $current, $next")
                return index
            }
        } else if (current > next) {
            if (increasing) {
                println("Suddenly decreasing: $report")
                return index
            }
            increasing = false
            if (current - next > 3) {
                println("More than 3: $report, $current, $next")
                return index
            }
        } else {
            println("Neither increase or decrease: $report, $current, $next")
            return index
        }
    }
    println("Safe report: $report")
    return null
}
