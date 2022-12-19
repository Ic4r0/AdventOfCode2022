package days

import utils.GetFiles

class Day02 {
    private val shapePoints = mapOf("A" to 1, "B" to 2, "C" to 3)
    private val roundPoints = mapOf(
        Pair("A", "B") to 6, Pair("B", "C") to 6, Pair("C", "A") to 6,
        Pair("A", "A") to 3, Pair("B", "B") to 3, Pair("C", "C") to 3,
        Pair("B", "A") to 0, Pair("C", "B") to 0, Pair("A", "C") to 0
    )

    private fun computeRoundPoints(currentPair: Pair<String, String?>): Int {
        val (_, mine) = currentPair
        val currentShapePoints = shapePoints[mine]
        val currentRoundPoints = roundPoints[currentPair]
        if (currentShapePoints != null && currentRoundPoints != null) {
            return currentShapePoints + currentRoundPoints
        }
        return 0
    }

    private fun part1(lines: List<List<String>>): Int {
        val results: MutableList<Int> = mutableListOf()
        val combinations = listOf(
            mapOf("X" to "A", "Y" to "B", "Z" to "C"),
            mapOf("X" to "A", "Y" to "C", "Z" to "B"),
            mapOf("X" to "B", "Y" to "A", "Z" to "C"),
            mapOf("X" to "B", "Y" to "C", "Z" to "A"),
            mapOf("X" to "C", "Y" to "A", "Z" to "B"),
            mapOf("X" to "C", "Y" to "B", "Z" to "A"),
        )

        combinations.forEach { combination ->
            var points = 0
            lines.forEach { (adversary, mine) ->
                points += computeRoundPoints(Pair(adversary, combination[mine]))
            }
            results.add(points)
        }
        return results.max()
    }

    private fun part2(lines: List<List<String>>): Int {
        val shapeToChoose =
            mapOf(
                Pair("A", "X") to "C",
                Pair("B", "X") to "A",
                Pair("C", "X") to "B",
                Pair("A", "Y") to "A",
                Pair("B", "Y") to "B",
                Pair("C", "Y") to "C",
                Pair("A", "Z") to "B",
                Pair("B", "Z") to "C",
                Pair("C", "Z") to "A",
            )
        var points = 0
        lines.forEach { (adversary, mine) ->
            points += computeRoundPoints(Pair(adversary, shapeToChoose[Pair(adversary, mine)]))
        }
        return points
    }

    fun selectPart(part: Int) {
        val lines = GetFiles().multipleStringLines(2).map { it.split(" ") }
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