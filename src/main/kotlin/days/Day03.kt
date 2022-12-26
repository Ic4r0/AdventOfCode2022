package days

import utils.GetFiles

class Day03 {
    val letters = listOf('a'..'z', 'A'..'Z').flatten()
    val numbers = (1..52).toList()

    var lettersToNumber = letters.zip(numbers).toMap()

    private fun part1(lines: List<Pair<String, String>>): Int {
        val duplicateChars: MutableList<Char> = mutableListOf()
        lines.forEach { (firstHalf, secondHalf) ->
            run searchChar@ {
                firstHalf.forEach { singleChar ->
                    if (secondHalf.contains(singleChar)) {
                        duplicateChars.add(singleChar)
                        return@searchChar
                    }
                }
            }
        }
        val charsToNumbers = duplicateChars.mapNotNull { lettersToNumber[it] }
        return charsToNumbers.sum()
    }

    private fun part2(lines: List<String>): Int {
        val duplicateChars: MutableList<Char> = mutableListOf()
         for (idx in lines.indices step 3) {
             for (char in lines[idx]) {
                 val isInSecondString = lines[idx + 1].contains(char)
                 val isInThirdString = lines[idx + 2].contains(char)
                 if (isInSecondString && isInThirdString) {
                     duplicateChars.add(char)
                     break
                 }
             }
        }
        val charsToNumbers = duplicateChars.mapNotNull { lettersToNumber[it] }
        return charsToNumbers.sum()
    }

    fun selectPart(part: Int) {
        val lines: List<String> = GetFiles().multipleStringLines(3)
        val dividedLines: List<Pair<String, String>> = lines.map {
            Pair(it.substring(0, it.length / 2), it.substring(it.length / 2))
        }
        when (part) {
            1 -> println(part1(dividedLines))
            2 -> println(part2(lines))
            else -> {
                println(part1(dividedLines))
                println(part2(lines))
            }
        }
    }
}