import java.io.File

fun main() {
    val allNumbers = File("src/year2020/day01/input.txt").readLines()
        .map(String::toInt)

    allNumbers.findPairOfSum(2020)?.let { println(it.first.times(it.second)) } ?: println("no pair found")

    allNumbers.associateWith { allNumbers.findPairOfSum(2020 - it) }
        .mapNotNull { it.value?.let { pair -> Triple(it.key, pair.first, pair.second) } }
        .forEach { println("${it.first} * ${it.second} * ${it.third} = ${it.toList().reduce(Int::times)}") }
}

fun List<Int>.findPairOfSum(sum: Int): Pair<Int, Int>? =
    this.associateBy { sum - it }
        .let { pairs -> this.mapNotNull { first -> pairs[first]?.let { second -> Pair(first, second) } } }
        .firstOrNull()
