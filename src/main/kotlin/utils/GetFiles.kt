package utils

import java.nio.file.Files
import java.nio.file.Paths


class GetFiles {
    fun singleLine() {

    }

    fun multipleStringLines(day: Int): List<String> {
        val file = Paths.get("src/main/resources/" + day.toString().padStart(2, '0'))
        return Files.readAllLines(file)
    }

    fun multipleIntegerLines(day: Int): List<Int?> {
        val file = Paths.get("src/main/resources/" + day.toString().padStart(2, '0'))
        return Files.readAllLines(file).map { it.toIntOrNull() }.toList()
    }
}