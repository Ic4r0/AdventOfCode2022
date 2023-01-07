package days

import utils.GetFiles

class Day08 {
    private fun checkIsVisible(lines: MutableList<MutableList<String>>, rowIdx: Int, colIdx: Int, valueToCheck: Int): Boolean {
        val rowListLeft = lines[rowIdx].subList(0, colIdx)
        val rowListRight = lines[rowIdx].subList(colIdx + 1, lines[rowIdx].size)
        val col = lines.map { row -> row[colIdx] }.toMutableList()
        val colListAbove = col.subList(0, rowIdx)
        val colListBelow = col.subList(rowIdx + 1, col.size)
        return rowListLeft.all { it.toInt() < valueToCheck } ||
                rowListRight.all { it.toInt() < valueToCheck } ||
                colListAbove.all { it.toInt() < valueToCheck } ||
                colListBelow.all { it.toInt() < valueToCheck }
    }

    private fun computeScenicScore(lines: MutableList<MutableList<String>>, maxRow: Int, maxCol: Int, rowIdx: Int, colIdx: Int): Int {
        if (rowIdx == 0 || rowIdx == maxRow || colIdx == 0 || colIdx == maxCol) {
            return 0
        }
        val valueToCheck = lines[rowIdx][colIdx].toInt()
        val rowListLeft = lines[rowIdx].subList(0, colIdx).reversed()
        val rowListRight = lines[rowIdx].subList(colIdx + 1, lines[rowIdx].size)
        val col = lines.map { row -> row[colIdx] }.toMutableList()
        val colListAbove = col.subList(0, rowIdx).reversed()
        val colListBelow = col.subList(rowIdx + 1, col.size)
        val cross = listOf(rowListLeft, rowListRight, colListAbove, colListBelow)
        var score = 1
        for (side in cross.indices) {
            var sideScore = 0
            for (idx in cross[side].indices) {
                sideScore = idx + 1
                if (cross[side][idx].toInt() >= valueToCheck) {
                    break
                }
            }
            score *= sideScore
        }
        return score
    }

    private fun part1(lines: MutableList<MutableList<String>>): Int {
        val edgeRow: List<Int> = listOf(0, lines.size)
        val edgeCol: List<Int> = listOf(0, lines[0].size)
        var visibleCounter = 0
        for (row in lines.indices) {
            for (col in lines[row].indices) {
                val valueToCheck = lines[row][col].toInt()
                if (edgeRow.contains(row) || edgeCol.contains(col) || checkIsVisible(lines, row, col, valueToCheck)) {
                    visibleCounter += 1
                }
            }
        }
        return visibleCounter
    }

    private fun part2(lines: MutableList<MutableList<String>>): Int {
        val maxRow: Int = lines.size
        val maxCol: Int = lines[0].size
        var maxScenicScore = 0
        for (row in lines.indices) {
            for (col in lines[row].indices) {
                val currentScenicScore = computeScenicScore(lines, maxRow, maxCol, row, col)
                maxScenicScore = Integer.max(maxScenicScore, currentScenicScore)
            }
        }
        return maxScenicScore
    }

    fun selectPart(part: Int) {
        val lines: List<String> = GetFiles().multipleStringLines(8)
        val splittedLines = lines.map { it.split("").drop(1).dropLast(1).toMutableList() }
                                                           .toMutableList()
        when (part) {
            1 -> println(part1(splittedLines))
            2 -> println(part2(splittedLines))
            else -> {
                println(part1(splittedLines))
                println(part2(splittedLines))
            }
        }
    }
}