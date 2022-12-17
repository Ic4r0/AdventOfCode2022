package days

import utils.GetFiles

class Day01 {
    private fun part1(lines: List<Int?>): Int {
        val caloriesForEachElf: MutableList<Int> = mutableListOf()
        var caloriesSum = 0
        lines.forEachIndexed { index, element ->
            if (element != null) {
                caloriesSum += element
            }
            if (lines.size == index + 1 || element == null) {
                caloriesForEachElf.add(caloriesSum)
                caloriesSum = 0
            }
        }
        return caloriesForEachElf.max()
    }

    private fun part2(lines: List<Int?>): Int {
        val caloriesForEachElf: MutableList<Int> = mutableListOf()
        var caloriesSum = 0
        lines.forEachIndexed { index, element ->
            if (element != null) {
                caloriesSum += element
            }
            if (lines.size == index + 1 || element == null) {
                caloriesForEachElf.add(caloriesSum)
                caloriesSum = 0
            }
        }
        caloriesForEachElf.sortDescending()
        return caloriesForEachElf.subList(0, 3).sum()
    }

    fun selectPart(part: Int) {
        val lines = GetFiles().multipleIntegerLines(1)
        when (part) {
            1 -> println(part1(lines))
            2 -> println(part2(lines))
            else -> {
                println(part1(lines))
                println(part2(lines))
            }
        }
    }
}