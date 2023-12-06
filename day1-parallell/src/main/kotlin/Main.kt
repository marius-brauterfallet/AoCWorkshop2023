import kotlinx.coroutines.*
import java.io.File

fun main() {
    val onelinerResult = File("src/main/resources/input.txt").readLines()
        .sumOf { "${it.first { char -> char.isDigit() }}${it.last { char -> char.isDigit() }}".toInt() }
        .also { println("Part 1 result: $it") }

    val input = File("src/main/resources/input.txt").readLines()

    val result = runBlocking(Dispatchers.Default) {
        input
            .map { line ->
                async {
                    val first = line.first { it.isDigit() }
                    val last = line.last { it.isDigit() }

                    "$first$last".toInt()
                }
            }.sumOf { it.await() }
    }

    println("Parallell result: $result")
}