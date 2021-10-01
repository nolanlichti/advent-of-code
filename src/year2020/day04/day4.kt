package year2020.day04

import java.io.File

fun main() {
    File("src/year2020/day04/input.txt").readLines()
        .joinToString(separator = " ") { it.ifEmpty { "|" } }
        .split("|")
        .map { passport ->
            passport.split(" ")
                .filter { it.isNotBlank() }
                .associate {
                    it.split(":")
                        .let { (key, value) -> Field.valueOf(key.uppercase()) to value }
                }
        }
        .count { passport ->
            passport.all { (key, value) -> key.validator(value) }
                    && passport.keys.filter { it != Field.CID }
                        .containsAll(Field.values().filter { it != Field.CID })
        }
        .let { println("$it passports are valid") }
}

enum class Field(val validator: (str: String) -> Boolean) {
    BYR({ isValidYear(it, 1920..2002) }),
    IYR({ isValidYear(it, 2010..2020) }),
    EYR({ isValidYear(it, 2020..2030) }),
    HGT({
        Regex("""(\d+)(cm|in)""").matchEntire(it)
            .let { res ->
                when (res?.groups?.get(2)?.value) {
                    "cm" -> res.groups[1]?.value!!.toInt() in 150..193
                    "in" -> res.groups[1]?.value!!.toInt() in 59..76
                    else -> false
                }
            }
    }),
    HCL({ it matches """^#[0-9a-f]{6}$""".toRegex() }),
    ECL({ it in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") }),
    PID({ it matches """^\d{9}$""".toRegex() }),
    CID({ true });

    companion object {
        fun isValidYear(year: String, validRange: IntRange) =
            year matches """\d{4}""".toRegex() && year.toInt() in validRange
    }
}
