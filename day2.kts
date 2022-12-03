import java.io.File

val input = readFile("data/day2.txt").trim()

println("part 1")

println(
  input.split("\n").sumOf {
    val parts = it.split(" ")
    val them = parts[0].single().code - 'A'.code
    val me = parts[1].single().code - 'X'.code

    3 * ((4 + me - them) % 3) + me + 1
  }
)

println("part 2")

println(
  input.split("\n").sumOf {
    val parts = it.split(" ")
    val them = parts[0].single().code - 'A'.code
    val outcome = parts[1].single().code - 'X'.code
    val me = ((outcome - 1) + them + 3) % 3

    3 * ((outcome + 3) % 3) + me + 1
  }
)

fun readFile(path: String): String = File(path).bufferedReader().use { it.readText() }
