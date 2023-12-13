import kotlinx.coroutines.*
import java.io.File

fun main() {
    // Oneliner
    val onelinerResult = File("src/main/resources/input.txt").readLines().sumOf { "${it.first { char -> char.isDigit() }}${it.last { char -> char.isDigit() }}".toInt() }

    val input = File("src/main/resources/input.txt").readLines()

    // Parallell løsning
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
