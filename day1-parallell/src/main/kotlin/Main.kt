import java.io.File

fun main() {
    val onelinerResult = File("src/main/resources/input.txt").readLines().sumOf { "${it.first { char -> char.isDigit() }}${it.last { char -> char.isDigit() }}".toInt() }.also { println("Part 1 result: $it") }
}