import java.io.File

val input = readFile("data/day3.txt").trim()

println("part 1")

println(
  input.split("\n").map {
    it.subSequence(0 until it.length / 2) to it.subSequence(it.length / 2 until it.length)
  }.sumOf {
    val union = it.first.toSet().intersect(it.second.toSet())
    val single = union.single()
    if (single.isLowerCase()) -('a'.code - single.code) + 1 else -('A'.code - single.code) + 1 + 26
  }
)

println("part 2")

println(
  input.split("\n").filter { it.isNotBlank() }
    .chunked(3)
    .sumOf { group ->
      val single = group[0].toSet().intersect(group[1].toSet()).intersect(group[2].toSet()).single()
      if (single.isLowerCase()) -('a'.code - single.code) + 1 else -('A'.code - single.code) + 1 + 26
    }
)

fun readFile(path: String): String = File(path).bufferedReader().use { it.readText() }
