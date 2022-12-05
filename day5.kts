import java.io.File

val columns: String =
  """    [V] [G]             [H]        
[Z] [H] [Z]         [T] [S]        
[P] [D] [F]         [B] [V] [Q]    
[B] [M] [V] [N]     [F] [D] [N]    
[Q] [Q] [D] [F]     [Z] [Z] [P] [M]
[M] [Z] [R] [D] [Q] [V] [T] [F] [R]
[D] [L] [H] [G] [F] [Q] [M] [G] [W]
[N] [C] [Q] [H] [N] [D] [Q] [M] [B]"""
    .splitByLine()
    .joinToString("\n") { it.padEnd(35, ' ') }

val input = readFile("data/day5.txt")

fun part1() {
  val piles: Map<Int, MutableList<Char>> =
    (1..9).associateWith { pile ->
      columns
        .splitByLine()
        .map { it[(pile - 1) * 4 + 1] }
        .filter { it.isLetter() }
        .toMutableList()
    }

  input.splitByLine().forEach {
    val a = it.split(' ')
    val to = piles[a[5].toInt()]!!
    val from = piles[a[3].toInt()]!!
    val count = a[1].toInt()

    (0 until count).forEach { to.add(0, from.removeFirst()) }
  }

  println(
    piles.entries
      .sortedBy { it.key }
      .joinToString(separator = "") { it.value.first().toString() }
  )
}

println("part 1")
part1()

fun part2() {
  val piles: Map<Int, MutableList<Char>> =
    (1..9).associateWith { pile ->
      columns
        .splitByLine()
        .map { it[(pile - 1) * 4 + 1] }
        .filter { it.isLetter() }
        .toMutableList()
    }

  input.splitByLine().forEach {
    val a = it.split(' ')
    val to = piles[a[5].toInt()]!!
    val from = piles[a[3].toInt()]!!
    val count = a[1].toInt()

    to.addAll(0, from.slice(0 until count))
    (0 until count).forEach { from.removeFirst() }
  }

  println(
    piles.entries
      .sortedBy { it.key }
      .joinToString(separator = "") { it.value.first().toString() }
  )
}

println("part 2")
part2()

fun readFile(path: String): String = File(path).bufferedReader().use { it.readText() }

fun String.splitByLine() = this.split("\n")
