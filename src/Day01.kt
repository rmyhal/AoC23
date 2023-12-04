fun main() {
  fun part1(input: List<String>): Int {
    var ans = 0
    input.forEach { str ->
      val firstDigit = str.first { it.isDigit() }
      val lastDigit = str.last { it.isDigit() }
      val calibration = "$firstDigit$lastDigit".toInt()
      ans += calibration
    }
    return ans
  }

  fun part2(input: List<String>): Int {
    var ans = 0
    input.forEach { str ->
      val firstDigit = spelled
        .mapIndexed { index, s -> index to str.indexOf(s) }
        .filter { it.second >= 0 }
        .minBy { it.second }
        .let { spelled[it.first] }
        .toCharDigit()

      val lastDigit = spelled
        .mapIndexed { index, s -> index to str.lastIndexOf(s) }
        .filter { it.second >= 0 }
        .maxBy { it.second }
        .let { spelled[it.first] }
        .toCharDigit()

      ans += firstDigit + lastDigit
    }
    return ans
  }

  val input = readInput("day-01")
  part1(input).println()
  part2(input).println()
}

val spelled = listOf(
  "1", "one",
  "2", "two",
  "3", "three",
  "4", "four",
  "5", "five",
  "6", "six",
  "7", "seven",
  "8", "eight",
  "9", "nine"
)

private operator fun Char.plus(other: Char): Int = "$this$other".toInt()

private fun String.toCharDigit(): Char = if (length == 1) {
  first()
} else {
  val index = spelled.indexOf(this) - 1
  spelled[index].first()
}
