import java.io.File

val input = readFile("day6.txt")

println("part 1")

for (i in 4..input.length) {
  if (input.substring((i - 4) until i).toSet().size == 4) {
    println(i)
    break
  }
}

println("part 2")

for (i in 14..input.length) {
  if (input.substring((i - 14) until i).toSet().size == 14) {
    println(i)
    break
  }
}

fun readFile(path: String): String = File(path).bufferedReader().use { it.readText() }
