import kotlin.math.absoluteValue

fun main() {


    fun part1(input: List<String>): Int {
        var safeSounter: Int = 0

        input.forEach {
            val levels = it.split(" ").map { level ->
                level.toInt()
            }
            var hits: Int = 0
            for (i in 1 until levels.size) {
                val diff = levels[i] - levels[i - 1]
                if (diff in 1..3) {
                    hits++
                } else if (diff in -3..-1) {
                    hits--
                } else {
                    break
                }
            }
            if (hits.absoluteValue == levels.size - 1) {
                safeSounter++
            }

        }
        return safeSounter
    }


    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)

    val input = readInput("Day02")
    part1(input).println()

    check(part2(testInput) == 4)
    part2(input).println()
}
