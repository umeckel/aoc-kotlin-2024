

fun main() {


    fun part1(input: List<String>): Int {
        var result = 0
        input.forEach {
            val regex = Regex("mul\\((?<first>\\d{1,3}),(?<second>\\d{1,3})\\)")
            val matches = regex.findAll(it)
            matches.forEach { matchResult ->
                result += matchResult.groupValues[1].toInt() * matchResult.groupValues[2].toInt()
            }
        }
        return result
    }


    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day03_test")
    check(part1(testInput1) == 161)

    val input = readInput("Day03")
    part1(input).println()

    val testInput2 = readInput("Day03_test2")
    check(part2(testInput2) == 48)
    part2(input).println()
}
