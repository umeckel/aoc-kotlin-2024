fun main() {
    fun splitAndSortLists(input: List<String>): Pair<MutableList<Int>, MutableList<Int>> {
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()

        input.forEach { item ->
            val parts = item.split(" ")
            val left = parts.first().toInt()
            val right = parts.last().toInt()
            leftList.add(left)
            rightList.add(right)
//            if (left !in leftList) leftList.add(left)
//            if (right !in rightList) rightList.add(right)

        }

        leftList.sort()
        rightList.sort()
        return Pair(leftList, rightList)
    }

    fun calculateDifferences(leftList: MutableList<Int>, rightList: MutableList<Int>): MutableList<Int> {
        val differences = mutableListOf<Int>()

        for (idx in 0 until leftList.size) {
            differences.add(kotlin.math.abs(leftList[idx] - rightList[idx]))
        }

        return differences
    }

    fun part1(input: List<String>): Int {

        val (leftList, rightList) = splitAndSortLists(input)

        val diffs = calculateDifferences(leftList, rightList)

        return diffs.sum()
    }

    fun multiplyWithOccurence(leftList: MutableList<Int>, rightList: MutableList<Int>): MutableList<Int> {
        val multiplied = mutableListOf<Int>()
        val occurrences = rightList.groupingBy { it }.eachCount()

        leftList.forEach { 
            multiplied.add(it * (occurrences[it] ?: 0))
        }
        return multiplied
    }

    fun part2(input: List<String>): Int {
        val (leftList, rightList) = splitAndSortLists(input)

        val multiplied = multiplyWithOccurence(leftList, rightList)

        return multiplied.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)

    val input = readInput("Day01")
    part1(input).println()

    check(part2(testInput) == 31)
    part2(input).println()
}
