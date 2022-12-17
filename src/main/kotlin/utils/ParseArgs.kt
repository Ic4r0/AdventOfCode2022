package utils

class ParseArgs {
    fun dayAndPart(args: Array<String>): Pair<Int, Int> {
        val firstValue = args[0].toInt(10)
        var secondValue = 0
        if (args.size > 1) {
            secondValue = args[1].toInt(10)
        }
        if (secondValue > 2) {
            throw Exception("2 is the maximum value for the daily exercise")
        }
        return Pair(firstValue, secondValue)
    }
}