package year2020.day03

import java.io.File

fun main() {
    val mapLines = File("src/year2020/day03/input.txt").readLines()

    listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)
        .map { trajectory ->
            val (dx, dy) = trajectory
            val width = mapLines[0].length
            mapLines.indices.count {
                it % dy == 0 && mapLines[it][it * dx / dy % width] == '#'
            }
            .let {
                println("number of trees is $it")
                it
            }
        }.reduce { a, b -> a.times(b) }
        .let {
            println("product is $it")
        }
}
