import java.io.File
import java.lang.RuntimeException

val input = readFile("data/day7.txt")

data class FileSummary(val name: String, val fileSize: Int)

println("part 1")

val directoryToFiles = mutableMapOf<String, MutableList<FileSummary>>()
val currentDirPath = mutableListOf("")

input
  .lines()
  .filter { it.isNotBlank() }
  .forEach { line ->
    val fullPath = currentDirPath.joinToString("/")
    // Initialize the list if it doesn't already exist.
    directoryToFiles[fullPath] = directoryToFiles[fullPath] ?: mutableListOf()
    when {
      line == "\$ ls" || line.startsWith("dir ") -> {
        // These lines aren't helpful.
      }
      line == "\$ cd .." -> {
        currentDirPath.removeLast()
      }
      line.startsWith("\$ cd ") -> {
        currentDirPath.add(line.removePrefix("\$ cd "))
      }
      line.first().isDigit() -> {
        val filesize = line.split(" ").first().toInt()
        val filename = line.split(" ")[1]
        if (directoryToFiles[fullPath]!!.any { it.name == filename }) {
          throw RuntimeException("file already exists in the tree!")
        } else {
          directoryToFiles[fullPath]!!.add(
            FileSummary(name = filename, fileSize = filesize)
          )
        }
      }
      else -> {
        throw RuntimeException("Unhandled line")
      }
    }
  }

val directoryToTotalFileSize =
  directoryToFiles.keys.associateWith { key ->
    directoryToFiles
      // Find all directories that begin with the same path (including itself).
      .filterKeys { it.startsWith(key) }
      // Sum all of the sizes in each of the folders.
      .values
      .sumOf { filesInDir -> filesInDir.sumOf { it.fileSize } }
  }
println(directoryToTotalFileSize.values.filter { it <= 100000 }.sum())

println("part 2")

val needToFreeUp = -(70000000 - directoryToTotalFileSize[""]!! - 30000000)
println(directoryToTotalFileSize.values.filter { it >= needToFreeUp }.minOf { it })

fun readFile(path: String): String = File(path).bufferedReader().use { it.readText() }
