package days

import utils.GetFiles

class Day06 {
    private fun computeMarkerPosition(line: String, desiredLength: Int): Int {
        var counter = 0
        while (line.substring(counter, counter + desiredLength).toSet().size != desiredLength) {
            counter++
        }
        return counter + desiredLength
    }
    private fun part1(line: String): Int {
        return computeMarkerPosition(line, 4)
    }

    private fun part2(line: String): Int {
        return computeMarkerPosition(line, 14)
    }

    fun selectPart(part: Int) {
        val line: String = GetFiles().multipleStringLines(6)[0]
        when (part) {
            1 -> println(part1(line))
            2 -> println(part2(line))
            else -> {
                println(part1(line))
                println(part2(line))
            }
        }
    }
}