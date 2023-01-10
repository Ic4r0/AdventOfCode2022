package days

import utils.GetFiles
import kotlin.math.abs

class Day09 {
    private var visitedTailPositions: MutableList<Pair<Int, Int>> = mutableListOf()

    private fun checkUnitDistance(tail: Pair<Int, Int>, head: Pair<Int, Int>): Boolean {
        val (tailX, tailY) = tail
        val (headX, headY) = head
        return abs(tailX - headX) <= 1 && abs(tailY - headY) <= 1
    }

    private fun newHeadPosition(currentHead: Pair<Int, Int>, direction: String): Pair<Int, Int> {
        val (headX, headY) = currentHead
        var step = Pair(0, 0)
        when (direction) {
            "U" -> step = Pair(0, 1)
            "D" -> step = Pair(0, -1)
            "L" -> step = Pair(-1, 0)
            "R" -> step = Pair(1, 0)
        }
        val (stepX, stepY) = step
        return Pair(headX + stepX, headY + stepY)
    }

    private fun newTailPosition(currentTail: Pair<Int, Int>, newHead: Pair<Int, Int>, direction: String): Pair<Int, Int> {
        if (checkUnitDistance(currentTail, newHead)) {
            return currentTail
        }
        val (headX, headY) = newHead
        var newTail = currentTail
        when (direction) {
            "U" -> newTail = Pair(headX, headY - 1)
            "D" -> newTail = Pair(headX, headY + 1)
            "L" -> newTail = Pair(headX + 1, headY)
            "R" -> newTail = Pair(headX - 1, headY)
        }
        visitedTailPositions.add(newTail)
        return newTail
    }

    private fun newTailsPosition(currentTails: List<Pair<Int, Int>>, newHead: Pair<Int, Int>): List<Pair<Int, Int>> {
        val newTails: MutableList<Pair<Int, Int>> = mutableListOf()
        for (tailNumber in currentTails.indices) {
            val referenceKnot = if (tailNumber == 0) newHead else newTails[tailNumber - 1]
            if (checkUnitDistance(currentTails[tailNumber], referenceKnot)) {
                newTails.add(currentTails[tailNumber])
                continue
            }
            val (referenceKnotX, referenceKnotY) = referenceKnot
            var (tailX, tailY) = currentTails[tailNumber]
            // X PART
            if (referenceKnotX < tailX) {
                tailX -= 1
            } else if (referenceKnotX > tailX) {
                tailX += 1
            }
            // Y PART
            if (referenceKnotY < tailY) {
                tailY -= 1
            } else if (referenceKnotY > tailY) {
                tailY += 1
            }
            val newTail = Pair(tailX, tailY)
            newTails.add(newTail)
        }
        visitedTailPositions.add(newTails.last())
        return newTails
    }

    private fun navigate(tail: Pair<Int, Int>, head: Pair<Int, Int>, direction: String, movement: Int): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        var currentHead = head
        var currentTail = tail
        for (step in 1..movement) {
            currentHead = newHeadPosition(currentHead, direction)
            currentTail = newTailPosition(currentTail, currentHead, direction)
        }
        return Pair(currentHead, currentTail)
    }

    private fun navigate(tails: List<Pair<Int, Int>>, head: Pair<Int, Int>, direction: String, movement: Int): Pair<Pair<Int, Int>, List<Pair<Int, Int>>> {
        var currentHead = head
        var currentTails = tails
        for (step in 1..movement) {
            currentHead = newHeadPosition(currentHead, direction)
            currentTails = newTailsPosition(currentTails, currentHead)
        }
        return Pair(currentHead, currentTails)
    }
    private fun part1(lines: List<Pair<String, Int>>): Int {
        visitedTailPositions = mutableListOf(Pair(0, 0))
        var tail = Pair(0, 0)
        var head = Pair(0, 0)
        for ((direction, movement) in lines) {
            val (newHead, newTail) = navigate(tail, head, direction, movement)
            tail = newTail
            head = newHead
        }
        return visitedTailPositions.toSet().size
    }

    private fun part2(lines: List<Pair<String, Int>>): Int {
        visitedTailPositions = mutableListOf(Pair(0, 0))
        var tails = listOf(Pair(0, 0), Pair(0, 0), Pair(0, 0), Pair(0, 0), Pair(0, 0), Pair(0, 0), Pair(0, 0), Pair(0, 0), Pair(0, 0))
        var head = Pair(0, 0)
        for ((direction, movement) in lines) {
            val (newHead, newTails) = navigate(tails, head, direction, movement)
            tails = newTails
            head = newHead
        }
        return visitedTailPositions.toSet().size
    }

    fun selectPart(part: Int) {
        val lines: List<String> = GetFiles().multipleStringLines(9)
        val regexStr = Regex("^(\\w+) (\\d+)\$")
        val regexFind = lines.map {
            val match = regexStr.find(it)!!.groups
            Pair(match[1]!!.value, match[2]!!.value.toInt())
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