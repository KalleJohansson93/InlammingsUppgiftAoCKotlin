import java.io.File

fun decoding(input: List<String>): Int {
    val sakerAttDecoda: List<String> = input.map { it.substringAfter("| ") }
    var räknare = 0

    for (it in sakerAttDecoda) {

        val a = it.indexOfFirst { it == ' ' }
        val b = it.indexOfFirst { it == ' ' } + it.substringAfter(' ').indexOfFirst { it == ' ' } + 1
        val c = it.indexOfLast { it == ' ' }
        val d = it.length

        if (a == 2 || a == 3 || a == 4 || a == 7) räknare++
        if (b - a - 1 == 2 || b - a - 1 == 3 || b - a - 1 == 4 || b - a - 1 == 7) räknare++
        if (c - b - 1 == 2 || c - b - 1 == 3 || c - b - 1 == 4 || c - b - 1 == 7) räknare++
        if (d - c - 1 == 2 || d - c - 1 == 3 || d - c - 1 == 4 || d - c - 1 == 7) räknare++
    }
    return räknare
}


fun decodingMerLättläst(input: List<String>): Int {
    val raderAttDecoda: List<String> = input.map { it.substringAfter("| ") }
    var räknare = 0

    for (helaRaden in raderAttDecoda) {

        val tal1 = helaRaden.indexOfFirst { it == ' ' }
        val tal2 = helaRaden.indexOfFirst { it == ' ' } + helaRaden.substringAfter(' ').indexOfFirst { it == ' ' } - tal1
        val tal3 = helaRaden.indexOfLast { it == ' ' } - (tal1 + tal2) - 2
        val tal4 = helaRaden.length - (tal1 + tal2 + tal3) - 3

        if (tal1 == 2 || tal1 == 3 || tal1 == 4 || tal1 == 7) räknare++
        if (tal2 == 2 || tal2 == 3 || tal2 == 4 || tal2 == 7) räknare++
        if (tal3 == 2 || tal3 == 3 || tal3 == 4 || tal3 == 7) räknare++
        if (tal4 == 2 || tal4 == 3 || tal4 == 4 || tal4 == 7) räknare++
    }
    return räknare
}


fun förbättradDecoding(input: List<String>): Int {
    val räknareAvTalen1478 = input.sumOf {
        it.substringAfter("| ")                                                          //output: "fabgced gc agc cdfg"
            .split(' ')                                                                //output: ["fabgced", "gc", "agc", "cdfg"]
            .count { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7 }
    }
    return räknareAvTalen1478
}



fun main() {
    val data = File("src\\main\\kotlin\\AoC2021Day8.txt").readLines()
    println("lösning: " + decoding(data))
    println("lösning: " + förbättradDecoding(data))
    //println(Day8(data).part1())
}


/*
Number 1:    Number 4:    Number 7:    Number 8:
 ....         ....         aaaa         aaaa
.    c       b    c       .    c       b    c
.    c       b    c       .    c       b    c
 ....         dddd         ....         dddd
.    f       .    f       .    f       e    f
.    f       .    f       .    f       e    f
 ....         ....         ....         gggg
*/






//https://github.com/ephemient/aoc2021/blob/main/kt/src/commonMain/kotlin/com/github/ephemient/aoc2021/Day8.kt
//Väldigt snygg och lättläst lösning. split, count och sumOf har verkligen gjort ett bra jobb här
class Day8(private val lines: List<String>) {
    fun part1(): Int = lines.sumOf {
        it.substringAfter(" | ")
            .split(' ')
            .count { it.length == 2 || it.length == 4 || it.length == 3 || it.length == 7 }
    }
}


//https://todd.ginsberg.com/post/advent-of-code/2021/day8/
//Krånglig lösning för att kunna lösa del 2
/*private class InputRow(val digitSegments: List<Set<Char>>) {

    class Day08(input: List<String>) {

        private val inputRows = input.map { InputRow.of(it) }

        fun solvePart1(): Int =
            inputRows.sumOf { row ->
                row.digitSegments.takeLast(4).count { it.size in setOf(2, 3, 4, 7) }
            }

        companion object {
            fun of(input: String): InputRow =
                InputRow(
                    input.split(" ").filterNot { it == "|" }.map { it.toSet() }
                )

        }
    }
}*/

//https://www.reddit.com/r/adventofcode/comments/rbj87a/2021_day_8_solutions/
//Tar en pair av två Listor som båda innehåller String
private fun part1(input: List<Pair<List<String>, List<String>>>): Int =
    input.sumOf { (_, v) -> v.count { it.length in setOf(2, 3, 4, 7) } }


