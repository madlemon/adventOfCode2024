package day5.part2

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

    val precedenceMap = orderingRules.groupBy({ it.second }, { it.first }).mapValues { it.value.toSet() }

    val comparator =  OrderingRulesComparator(precedenceMap)

    val result = updates.sumOf { update ->
        if (update.isInCorrectOrder(precedenceMap)) {
            0
        } else {
            update.sortedWith(comparator).getMiddlePageNumber().toInt()
        }
    }

    println(result)
}

typealias Page = String

private fun List<Page>.isInCorrectOrder(precedenceMap: Map<Page, Set<Page>>): Boolean {
    for (i in indices) {
        val currentPage = this[i]
        val remainingPages = drop(i + 1)
        if (precedenceMap[currentPage]?.any { it in remainingPages } == true) {
            return false
        }
    }
    return true
}

private fun List<Page>.getMiddlePageNumber(): Page = this[this.size / 2]

class OrderingRulesComparator(private val precedenceMap: Map<Page, Set<Page>>) : Comparator<Page> {
    override fun compare(o1: Page, o2: Page): Int {
        return when {
            o1 == o2 -> 0
            hasPrecedence(o1, o2) -> -1
            hasPrecedence(o2, o1) -> 1
            else -> 0
        }
    }

    private fun hasPrecedence(first: Page, second: Page): Boolean {
        return precedenceMap[second]?.contains(first) == true
    }
}



