import java.io.File

val input = readFile("data/day4.txt")

println("part 1")

println(
  input
    .split("\n")
    .map {
      val a = it.split(",")
      a[0].split("-")[0].toInt()..a[0].split("-")[1].toInt() to
          a[1].split("-")[0].toInt()..a[1].split("-")[1].toInt()
    }
    .count { trueEitherWay(it.first.toSet(), it.second.toSet()) { a, b -> a.containsAll(b) } }
)

println("part 2")

println(
  input
    .split("\n")
    .map {
      val a = it.split(",")
      a[0].split("-")[0].toInt()..a[0].split("-")[1].toInt() to
          a[1].split("-")[0].toInt()..a[1].split("-")[1].toInt()
    }
    .count { it.first.intersect(it.second).isNotEmpty() }
)

fun readFile(path: String): String = File(path).bufferedReader().use { it.readText() }
fun <A> trueEitherWay(a: A, b: A, f: (A, A) -> Boolean) = f(a, b) || f(b, a)
