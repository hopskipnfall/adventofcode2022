import java.io.File

val input = readFile("data/day1.txt")

println("part 1")

println(
  input.split("\n\n").map { it.split("\n").map { it.toInt() }.sum() }.maxOrNull()
)

println("part 2")

println(
  input
    .split("\n\n")
    .map { it.split("\n").map { it.toInt() }.sum() }
    .asSequence()
    .sortedDescending()
    .take(3)
    .sum()
)

fun readFile(path: String): String = File(path).bufferedReader().use { it.readText() }
