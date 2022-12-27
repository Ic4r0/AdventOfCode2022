import days.Day01
import days.Day02
import days.Day03
import days.Day04
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
        else -> throw Exception("No code for the selected day is available yet")
    }
}