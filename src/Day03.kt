fun main() {
    fun find_mul_statements(memory: String): Int {
        var result = 0
        val regex = Regex("mul\\((?<first>\\d{1,3}),(?<second>\\d{1,3})\\)")
        val matches = regex.findAll(memory)
        matches.forEach { matchResult ->
            result += matchResult.groupValues[1].toInt() * matchResult.groupValues[2].toInt()
        }
        return result
    }


    fun part1(input: List<String>): Int {
        var result = 0
        input.forEach {
            result += find_mul_statements(it)
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        var enable = true
        input.forEach {

            val regex = Regex("(mul\\((?<first>\\d{1,3}),(?<second>\\d{1,3})\\))|do\\(\\)|don't\\(\\)")
            var match = regex.find(it)
            while (match != null) {
                when (match.value) {
                    "do()" -> enable = true
                    "don't()" -> enable = false
                    else -> {
                        if (enable) {
                            result += match.groupValues[2].toInt() * match.groupValues[3].toInt()
                        }
                    }
                }
                match = match.next()
            }
        }
        return result
    }

    fun part2_does_not_work(input: List<String>): Int {
        var result = 0
        var active = true
        input.forEach {
            var start_idx = 0
            var end_idx = 0
            if (active.not()) {
                start_idx = it.findAnyOf(listOf("do()"), startIndex = end_idx)?.first ?: it.lastIndex
                active = true
            }

            while (end_idx != it.lastIndex) {
                end_idx = it.findAnyOf(listOf("don't()"), startIndex = start_idx)?.first ?: it.lastIndex
                result += find_mul_statements(it.substring(start_idx, end_idx))
                if (end_idx != it.lastIndex) {
                    start_idx = it.findAnyOf(listOf("do()"), startIndex = end_idx)?.first ?: it.lastIndex
                    if (start_idx == it.lastIndex) {
                        //active = false
                        break
                    }
                }
            }
        }
        return result
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
