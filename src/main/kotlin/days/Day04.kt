package days

import utils.GetFiles

class Day04 {
    private fun part1(lines: List<Pair<Pair<Int?, Int?>, Pair<Int?, Int?>>>): Int {
        var counter = 0
        lines.forEach {
            val (firstElf, secondElf) = it
            val (firstIdxStart, firstIdxEnd) = firstElf
            val (secondIdxStart, secondIdxEnd) = secondElf
            val isFirstInSecond = firstIdxStart!! >= secondIdxStart!! && firstIdxEnd!! <= secondIdxEnd!!
            val isSecondInFirst = firstIdxStart <= secondIdxStart && firstIdxEnd!! >= secondIdxEnd!!
            if (isFirstInSecond || isSecondInFirst) {
                counter++
            }
        }
        return counter
    }

    private fun part2(lines: List<Pair<Pair<Int?, Int?>, Pair<Int?, Int?>>>): Int {
        var counter = 0
        lines.forEach {
            val (firstElf, secondElf) = it
            val (firstIdxStart, firstIdxEnd) = firstElf
            val (secondIdxStart, secondIdxEnd) = secondElf
            val isFirstOverlapSecond = firstIdxStart!! in secondIdxStart!!..secondIdxEnd!! || firstIdxEnd in secondIdxStart..secondIdxEnd
            val isSecondOverlapFirst = secondIdxStart in firstIdxStart..firstIdxEnd!! || secondIdxEnd in firstIdxStart..firstIdxEnd
            if (isFirstOverlapSecond || isSecondOverlapFirst) {
                counter++
            }
        }
        return counter
    }

    fun selectPart(part: Int) {
        val lines: List<String> = GetFiles().multipleStringLines(4)
        val regexStr = Regex("^(\\d+)-(\\d+),(\\d+)-(\\d+)\$")
        val regexFind = lines.mapNotNull {
            val match = regexStr.find(it)?.groups
            if (match != null) {
                Pair(
                    Pair(match[1]?.value?.toInt(), match[2]?.value?.toInt()),
                    Pair(match[3]?.value?.toInt(), match[4]?.value?.toInt())
                )
            } else {
                null
            }
        }
        when (part) {
            1 -> println(part1(regexFind))
            2 -> println(part2(regexFind))
            else -> {
                println(part1(regexFind))
                println(part2(regexFind))
            }
        }
    }
}