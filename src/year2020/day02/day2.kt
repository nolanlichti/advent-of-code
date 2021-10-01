import java.io.File

fun main() {
    val matcher = Regex("""(\d+)-(\d+) ([a-z]): ([a-z]+)""")
    val passwords = File("src/year2020/day02/input.txt").readLines()

    passwords.sumOf {
        val res = matcher.matchEntire(it)!!
            .destructured
            .let { (min, max, letter, password) ->
                val count = password.count(letter[0]::equals)
                if (count >= min.toInt() && count <= max.toInt()) 1 else 0
            }
        res
    }


    passwords.sumOf {
        val res = matcher.matchEntire(it)!!
            .destructured
            .let { (pos1, pos2, letter, password) ->
                val doesPos1Match = doesPositionMatch(password, letter[0], pos1.toInt() - 1)
                val doesPos2Match = doesPositionMatch(password, letter[0], pos2.toInt() - 1)
                if (doesPos1Match.xor(doesPos2Match)) 1 else 0

            }
        res
    }
        .let { println(it) }
}

fun doesPositionMatch(str: String, char: Char, position: Int): Boolean =
    position < str.length && char == str[position]

