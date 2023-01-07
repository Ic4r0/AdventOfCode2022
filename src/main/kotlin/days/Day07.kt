package days

import utils.GetFiles

class Day07 {
    private fun computeSizes(lines: List<String>): Map<Int, Int> {
        val sizes: MutableMap<Int, Int> = mutableMapOf()
        val stack: MutableList<Int> = mutableListOf()
        val filteredLines = lines.filter {
            !it.contains("$ ls") &&
            !it.contains("dir ")
        }
        for (idx in filteredLines.indices) {
            val line = filteredLines[idx]
            if (line.startsWith("$ cd ..")) {
                stack.removeLast()
            } else if (line.startsWith("$ cd")) {
                stack.add(idx)
                sizes[idx] = 0
            } else {
                val fileSize = line.split(" ")[0].toInt()
                stack.forEach {
                    sizes[it] = sizes[it]!! + fileSize
                }
            }
        }
        return sizes
    }

    private fun part1(lines: List<String>): Int {
        val sizes = computeSizes(lines)
        return sizes.values.filter { it < 100_000 }.sum()
    }

    private fun part2(lines: List<String>): Int {
        val availableDiskSpace = 70_000_000
        val unusedDiskSpace = 30_000_000
        val sizes = computeSizes(lines).values.sorted()
        val totalAmountUsedSpace = sizes.last()
        val spaceLeftToFree = unusedDiskSpace - (availableDiskSpace - totalAmountUsedSpace)
        return sizes.find { it >= spaceLeftToFree }!!
    }

    fun selectPart(part: Int) {
        val lines: List<String> = GetFiles().multipleStringLines(7)
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