package days

import utils.CloneElements
import utils.GetFiles

class Day05 {
    private fun part1(stacks: MutableMap<Int, MutableList<String>>, procedure: MutableList<Triple<Int, Int, Int>>): String {
        val newStacks = CloneElements().cloneMapOfLists(stacks)
        for ((elementsNumber, start, finish) in procedure) {
            repeat(elementsNumber) {
                newStacks[start]?.removeLast()?.let { removed -> newStacks[finish]?.add(removed) }
            }
        }
        var output = ""
        newStacks.keys.forEach {
            output += newStacks[it]?.last()
        }
        return output
    }

    private fun part2(stacks: MutableMap<Int, MutableList<String>>, procedure: MutableList<Triple<Int, Int, Int>>): String {
        val newStacks = CloneElements().cloneMapOfLists(stacks)
        for ((elementsNumber, start, finish) in procedure) {
            val originalStack = newStacks[start]!!
            val greaterNumElementsThanSize = originalStack.size < elementsNumber
            val staticStack = if (greaterNumElementsThanSize) mutableListOf() else originalStack.subList(0, originalStack.size - elementsNumber)
            val movedStack = if (greaterNumElementsThanSize) originalStack.toMutableList() else originalStack.subList(originalStack.size - elementsNumber, originalStack.size)
            newStacks[start] = staticStack
            newStacks[finish]?.addAll(movedStack)
        }
        var output = ""
        newStacks.keys.forEach {
            if (newStacks[it]!!.isNotEmpty()) {
                output += newStacks[it]?.last()
            }
        }
        return output
    }

    fun selectPart(part: Int) {
        val lines: List<String> = GetFiles().multipleStringLines(5)
        var isEmptyLine = false
        val stacksTemp: MutableList<String> = mutableListOf()
        val procedure: MutableList<Triple<Int, Int, Int>> = mutableListOf()
        lines.forEach {
            if (it.isBlank() || it.isEmpty()) {
                isEmptyLine = true
            } else if (!isEmptyLine) {
                stacksTemp.add(it)
            } else {
                val regexProcedure = Regex("^move (\\d+) from (\\d+) to (\\d+)\$")
                val procedureGroups = regexProcedure.find(it)?.groups!!
                procedure.add(Triple(
                    procedureGroups[1]!!.value.toInt(),
                    procedureGroups[2]!!.value.toInt(),
                    procedureGroups[3]!!.value.toInt()
                ))
            }
        }
        val regexStack = Regex("(\\d+)\$")
        val lastStack = regexStack.find(stacksTemp.last()[stacksTemp.last().length - 2].toString())
                                        ?.groups
                                        ?.get(1)!!
                                        .value
                                        .toInt()
        val stacks: MutableMap<Int, MutableList<String>> = mutableMapOf()
        for (stackIdx in 1 .. lastStack) {
            val stackStringIdx = (stackIdx - 1) * 4 + 1
            stacksTemp.subList(0, stacksTemp.size - 1).reversed().forEach {
                if (stackStringIdx < it.length - 1 && it[stackStringIdx] != ' ') {
                    if (stacks[stackIdx] == null) {
                        stacks[stackIdx] = mutableListOf()
                    }
                    stacks[stackIdx]?.add(it[stackStringIdx].toString())
                }
            }
        }
        when (part) {
            1 -> println(part1(stacks, procedure))
            2 -> println(part2(stacks, procedure))
            else -> {
                println(part1(stacks, procedure))
                println(part2(stacks, procedure))
            }
        }
    }
}