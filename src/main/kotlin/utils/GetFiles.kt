package utils

import java.nio.file.Files
import java.nio.file.Paths


class GetFiles {
    fun multipleStringLines(day: Int): List<String> {
        val file = Paths.get("src/main/resources/" + day.toString().padStart(2, '0'))
        return Files.readAllLines(file)
    }

    fun multipleIntegerLines(day: Int): List<Int?> {
        return multipleStringLines(day).map { it.toIntOrNull() }.toList()
    }
}