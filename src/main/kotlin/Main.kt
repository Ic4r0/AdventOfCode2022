import days.*
import utils.ParseArgs

fun main(args: Array<String>) {
    if (args.isEmpty() || args.size > 2) {
        throw Exception("At least one argument and a maximum of two arguments are required for this application")
    }

    val (day, part) = ParseArgs().dayAndPart(args)
    when (day) {
        1 -> Day01().selectPart(part)
        2 -> Day02().selectPart(part)
        3 -> Day03().selectPart(part)
        4 -> Day04().selectPart(part)
        5 -> Day05().selectPart(part)
        6 -> Day06().selectPart(part)
        7 -> Day07().selectPart(part)
        8 -> Day08().selectPart(part)
        else -> throw Exception("No code for the selected day is available yet")
    }
}