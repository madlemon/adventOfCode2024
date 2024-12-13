package day5.part1

import java.io.File

private const val EXAMPLE_ORDERING_RULES = "day5/exampleOrderingRules.txt"
private const val EXAMPLE_UPDATES = "day5/exampleUpdates.txt"

private const val INPUT_ORDERING_RULES = "day5/inputOrderingRules.txt"
private const val INPUT_UPDATES = "day5/inputUpdates.txt"

fun main() {
    val orderingRules = File(INPUT_ORDERING_RULES)
        .useLines { it.toList() }
        .map {
            val split = it.split('|')
            Pair(split[0], split[1])
        }

    val updates = File(INPUT_UPDATES)
        .useLines { it.toList() }
        .map { it.split(',') }

    fun notBreakingAnyRule(update: List<String>) = { pageIndex: Int, page: String ->
        orderingRules.none { (first, second) ->
            second == page && update.drop(pageIndex + 1).contains(first)
        }
    }

    val result = updates.sumOf { update ->
        val isInRightOrder = update.allIndexed(notBreakingAnyRule(update))
        if (isInRightOrder) {
            getMiddlePageNumber(update).toInt()
        } else {
            0
        }
    }

    println(result)
}


private fun getMiddlePageNumber(list: List<String>): String = list[list.size / 2]

private inline fun List<String>.allIndexed(predicate: (index: Int, String) -> Boolean): Boolean {
    for (i in indices) if (!predicate(i, this[i])) return false
    return true
}

