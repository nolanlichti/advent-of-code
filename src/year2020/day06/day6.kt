package year2020.day06

import java.io.File

fun main() {
    val lineSeparator = System.lineSeparator();
    val forms = File("src/year2020/day06/input.txt").readText()
        .trim()
        .split("$lineSeparator$lineSeparator")
        .map { it.split(lineSeparator).map(String::toSet) }
        .filter { it.isNotEmpty() }

    println(forms.totalAfterMap { list, list2 -> list.union(list2) })
    println(forms.totalAfterMap { list, list2 -> list.intersect(list2) })
}

fun List<List<Set<Char>>>.totalAfterMap(mapper: (Set<Char>, Set<Char>) -> Set<Char>) = this.sumOf { it.reduce(mapper).size }
