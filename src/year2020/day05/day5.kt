package year2020.day05

import java.io.File

fun main() {

    val seats = File("src/year2020/day05/input.txt")
        .readLines()
        .map { code ->
            code.map {
                when (it) {
                    'F', 'L' -> '0'
                    'B', 'R' -> '1'
                    else -> throw Exception("what")
                }
            }
                .joinToString("")
        }
        .map { it.substring(0 until 7).toInt(2) to (it.substring(7 until 10).toInt(2)) }
        .groupBy { it.first }

    seats.values.flatten()
        .maxOf { (row, column) -> row.times(8).plus(column) }
        .let(::println)

    val notFull = seats.filterNot { it.value.count() == 8 }

    notFull[notFull.keys.sorted()[notFull.keys.size / 2]]
        .let { notFullSeats ->
            val row = notFullSeats!![0].first
            val unoccupied = (0..7).toList().minus(notFullSeats.map { it.second })
            println("$notFullSeats - ${row * 8 + unoccupied.first()}")
        }
}
