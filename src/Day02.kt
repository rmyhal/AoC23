fun main() {
  fun part1(input: List<String>): Int {
    var ans = 0
    input.forEach { game ->
      val gameId = game
        .substringAfter("Game ").substringBefore(':')
        .toInt()

      var validGame = true
      game
        .substringAfter(": ")
        .split(';')
        .map { subset ->
          subset
            .split(',')
            .map { it.trim() }
            .groupBy(
              keySelector = { it.split(' ').last() },
              valueTransform = { it.split(' ').first() }
            )
            .mapValues { entry -> entry.value.sumOf { it.toInt() } }
        }
        .forEach { map ->
          if (map.getOrDefault("red", 0) > 12 ||
            map.getOrDefault("green", 0) > 13 ||
            map.getOrDefault("blue", 0) > 14
          ) {
            validGame = false
          }
        }

      if (validGame) {
        ans += gameId
      }
    }
    return ans
  }

  fun part2(input: List<String>): Int {
    return input.sumOf { str ->
      str
        .substringAfter(": ")
        .replace(';', ',')
        .split(',')
        .map { it.trim() }
        .groupBy(
          keySelector = { it.split(' ').last() },
          valueTransform = { it.split(' ').first().toInt() }
        )
        .mapValues { it.value.max() }
        .map { it.value }
        .reduce { acc, i -> acc * i }
    }
  }

  val input = readInput("day-02")
  part1(input).println()
  part2(input).println()
}